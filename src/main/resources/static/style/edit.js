window.addEventListener('load', function () {
  const url = '/' + document.title.toLowerCase();
  console.log('url:%o', url);

  const tableViewName = $('input[name="tableViewName"]').val();

  // 送信処理
  $(function () {
    $('button').click(function () {
      let message = '';
      if (url == '/insert') {
        message = 'に登録しました。';
      } else if (url == '/update') {
        message = 'を更新しました。';
      } else if (url == '/delete') {
        message = 'から削除しました。';
      }

      let form = $(this).parent().parent().find('form');
      console.log('$form:%o', form);

      // フォーム送信
      form.submit();
      $('.modal-body').find('p').remove(); //モーダルウィンドウの表示前に<p>タグを削除する
      $('.modal-body').append('<p>' + tableViewName + message + '</p>'); //<p>タグの追加。
      $('#successMsg').modal('show'); //モーダルウィンドウを表示する
    });
  });
});
