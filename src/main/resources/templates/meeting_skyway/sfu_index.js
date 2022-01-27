import "https://cdn.webrtc.ecl.ntt.com/skyway-4.4.3.js";

const Peer = window.Peer;
console.log(Peer);

(async function main() {
    const localVideo = document.getElementById('js-local-stream'); // 自分のカメラ映像
    const joinTrigger = document.getElementById('js-join-trigger'); // join ボタン
    const leaveTrigger = document.getElementById('js-leave-trigger'); // leave ボタン
    const remoteVideos = document.getElementById('js-remote-streams'); // 他人のカメラ映像
    const roomId = document.getElementById('js-room-id'); // Room name
    const roomMode = document.getElementById('js-room-mode'); // ルームモード（mesh,sfu）
    const localText = document.getElementById('js-local-text'); // メッセージ入力欄
    const sendTrigger = document.getElementById('js-send-trigger'); // send ボタン
    const messages = document.getElementById('js-messages'); // メッセージ表示欄
    const meta = document.getElementById('js-meta'); // 参考表示欄
    const sdkSrc = document.querySelector('script[src*=skyway]'); //

    // 参考表示欄にテキストを代入
    meta.innerText = `
    UA: ${navigator.userAgent}
    SDK: ${sdkSrc ? sdkSrc.src : 'unknown'}
  `.trim();

    // 現在の通信方法の取得
    // const getRoomModeByHash = () => (location.hash === '#sfu' ? 'sfu' : 'mesh');
    const getRoomModeByHash = () => "sfu";

    // Roomの現在の通信方法の表示
    roomMode.textContent = getRoomModeByHash();
    window.addEventListener(
        'hashchange',
        () => (roomMode.textContent = getRoomModeByHash())
    );

    // 自身のメディア情報（カメラ映像、音声）取得
    const localStream = await navigator.mediaDevices
        .getUserMedia({
            audio: true,
            video: true,
        })
        .catch(console.error);

    // Render local stream(自分のカメラ映像を整形)
    localVideo.muted = true;
    localVideo.srcObject = localStream;
    localVideo.playsInline = true;
    await localVideo.play().catch(console.error);

    // eslint-disable-next-line require-atomic-updates
    const peer = (window.peer = new Peer({
        key: window.__SKYWAY_KEY__,
        debug: 3,
    }));

    // Register join handler（joinボタンをクリックしたとき）
    joinTrigger.addEventListener('click', () => {

        // Note that you need to ensure the peer has connected to signaling server
        // before using methods of peer instance.
        if (!peer.open) {
            return;
        }

        // ルームに参加
        const room = peer.joinRoom(roomId.value, {
            mode: "sfu",
            stream: localStream,
            videoCodec: 'H264',
            audioCodec: 'PCMCIA'
        });

        room.once('open', () => {
            messages.textContent += '=== You joined ===\n';
        });
        room.on('peerJoin', peerId => {
            messages.textContent += `=== ${peerId} joined ===\n`;
        });

        // Render remote stream for new peer join in the room(他の参加者のストリームを整形)
        room.on('stream', async stream => {
            const newVideo = document.createElement('video');
            newVideo.srcObject = stream;
            newVideo.playsInline = true;
            // mark peerId to find it later at peerLeave event(退室するときに検索できるようpeerIDをつける)
            newVideo.setAttribute('data-peer-id', stream.peerId);
            remoteVideos.append(newVideo);
            await newVideo.play().catch(console.error);
        });

        // メッセージと送信者を表示
        room.on('data', ({ data, src }) => {
            // Show a message sent to the room and who sent
            messages.textContent += `${src}: ${data}\n`;
        });

        // for closing room members(メンバーが部屋を退室するとき)
        room.on('peerLeave', peerId => {
            const remoteVideo = remoteVideos.querySelector(
                `[data-peer-id="${peerId}"]`
            );
            remoteVideo.srcObject.getTracks().forEach(track => track.stop());
            remoteVideo.srcObject = null;
            remoteVideo.remove();

            messages.textContent += `=== ${peerId} left ===\n`;
        });

        // for closing myself（退出）
        room.once('close', () => {
            sendTrigger.removeEventListener('click', onClickSend);
            messages.textContent += '== You left ===\n';
            Array.from(remoteVideos.children).forEach(remoteVideo => {
                remoteVideo.srcObject.getTracks().forEach(track => track.stop());
                remoteVideo.srcObject = null;
                remoteVideo.remove();
            });
        });

        sendTrigger.addEventListener('click', onClickSend);
        leaveTrigger.addEventListener('click', () => room.close(), { once: true });

        // sendボタンをクリックされたら
        function onClickSend() {
            // Send message to all of the peers in the room via websocket(メッセージの送信)
            room.send(localText.value);

            messages.textContent += `${peer.id}: ${localText.value}\n`;
            localText.value = '';
        }
    });

    peer.on('error', console.error);
})();
