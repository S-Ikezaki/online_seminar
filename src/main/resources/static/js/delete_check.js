function func1(path) {
    let result = confirm('本当に削除しますか');

    let form = document.getElementsByTagName('form')[0];
    if (result) {
        console.log('削除しました');
        form.action = path;
        form.method = "post";
        form.submit();
    } else {
        console.log('削除をとりやめました');
    }
}