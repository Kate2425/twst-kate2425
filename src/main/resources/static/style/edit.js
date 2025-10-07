window.addEventListener('load', function () {
  const url = '/' + document.title.toLowerCase();
  console.log('url:%o', url);

  const tableViewName = $('input[name="tableViewName"]').val();

  // 送信処理
  $(function () {
    $('#submit').click(async function () {
      console.log('data:%o', $('form').serialize());
      $('.modal-body').find('p').remove(); //モーダルウィンドウの表示前に<p>タグを削除する
      $('.modal-body').append('<p>' + tableViewName + 'に登録します。</p>'); //<p>タグの追加。
      $('#successMsg').modal('show'); //モーダルウィンドウを表示する
      await axios
        .post(url, {
          data: $('form').serialize(),
        })
        // 成功
        .then(response => {
          console.log('response:' + response.data);
        })
        // エラー
        .catch(error => {
          if (error.response) {
            console.error('HTTPエラー:', error.response.status);
            console.error('data:', error.response.data);
          } else if (error.request) {
            console.error('レスポンスなし:', error.request);
          } else {
            console.error('リクエスト設定エラー:', error.message);
          }
        });
    });
  });
});
