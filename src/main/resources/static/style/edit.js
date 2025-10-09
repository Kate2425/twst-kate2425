window.addEventListener('load', function () {
  const url = '/' + document.title.toLowerCase();
  console.log('url:%o', url);

  const tableViewName = $('input[name="tableViewName"]').val();

  // 送信処理
  $(function () {
    $('button').click(async function () {
      let message = '';
      if (url == '/insert') {
        message = 'に登録しました。';
      } else if (url == '/update') {
        message = 'を更新しました。';
      } else if (url == '/delete') {
        message = 'から削除しました。';
      }

      let $form = $(this).parent().parent().find('form');
      console.log('$form:%o', $form);

      let cardForm = $form.serialize();
      console.log('cardForm:%o', cardForm);

      const postData = new URLSearchParams(cardForm);
      console.log('postData:%o', postData);

      await axios
        .post(url, postData, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
          },
        })
        // 成功
        .then(response => {
          console.log('response:' + response.data);
          $('.modal-body').find('p').remove(); //モーダルウィンドウの表示前に<p>タグを削除する
          $('.modal-body').append('<p>' + tableViewName + message + '</p>'); //<p>タグの追加。
          $('#successMsg').modal('show'); //モーダルウィンドウを表示する
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
