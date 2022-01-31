// import "https://cdn.webrtc.ecl.ntt.com/skyway-4.4.3.js";

// let localStream;
//
// let peer;

alert("JSファイル");


// // カメラ映像取得
// navigator.mediaDevices.getUserMedia({video: true, audio: true})
//     .then(stream => {
//         // 成功時にvideo要素にカメラ映像をセットし、再生
//         const videoElm = document.getElementById('my-video');
//         videoElm.srcObject = stream;
//         videoElm.play();
//         // 着信時に相手にカメラ映像を返せるように、グローバル変数に保存しておく
//         localStream = stream;
//     }).catch(error => {
//     // 失敗時にはエラーログを出力
//     console.error('mediaDevice.getUserMedia() error:', error);
//     return;
// });
//
// peer = new Peer({
//     key: 'b61e8381-31ba-47a5-a59c-ad88ab1472a2',
//     debug: 3
// });
//
// peer.on('open', () => {
//     document.getElementById('my-id').textContent = peer.id;
// });
//
// // 発信処理
// document.getElementById('make-call').onclick = () => {
//     const theirID = document.getElementById('their-id').value;
//     const mediaConnection = peer.call(theirID, localStream);
//     console.log(theirID);
//     setEventListener(mediaConnection);
// };
//
// // イベントリスナを設置する関数
// const setEventListener = mediaConnection => {
//     mediaConnection.on('stream', stream => {
//         // video要素にカメラ映像をセットして再生
//         const videoElm = document.getElementById('their-video')
//         videoElm.srcObject = stream;
//         videoElm.play();
//     });
// }
//
// document.getElementById('close-call').onclick = () => {
//     peer.destroy();
// }
//
// peer.on('call', mediaConnection => {
//     mediaConnection.answer(localStream);
//     setEventListener(mediaConnection);
// });
//
// peer.on('close', () => {
//     alert('通信が切断しました。');
//     window.close();
// });

// const makeCall = document.getElementById('make-call').onsubmit = () => {
//
// }
