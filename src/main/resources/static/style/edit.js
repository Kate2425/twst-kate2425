window.addEventListener('load', function () {
  const url = '/' + document.title.toLowerCase();
  console.log('url:%o', url);

  const tableViewName = $('input[name="tableViewName"]').val();
  let characterViewName;

  $(function () {
    if (url == '/insert') {
      $('[name=name]').change(function () {
        // 選択されているvalue属性値を取り出す
        let val = $('[name=name]').val();
        console.log(val);
        // 選択されている表示文字列を取り出す
        characterViewName = $('[name=name] option:selected').text();
        console.log(characterViewName);
      });
    }
  });

  // 送信処理
  $(function () {
    $('button').click(function () {
      if (url != '/insert') {
        characterViewName = $(this)
          .parent()
          .parent()
          .find('input[name="characterViewName"]')
          .val();
        console.log(characterViewName);
      }

      let message = '';
      if (url == '/insert') {
        message = tableViewName + ' ' + characterViewName + 'を登録しました。';
      } else if (url == '/update') {
        message = tableViewName + ' ' + characterViewName + 'を更新しました。';
      } else if (url == '/delete') {
        message = tableViewName + ' ' + characterViewName + 'を削除しました。';
      }

      let form = $(this).parent().parent().find('form');
      console.log('$form:%o', form);

      // フォーム送信
      form.submit();
      $('.modal-body').find('p').remove(); //モーダルウィンドウの表示前に<p>タグを削除する
      $('.modal-body').append('<p>' + message + '</p>'); //<p>タグの追加。
      $('#successMsg').modal('show'); //モーダルウィンドウを表示する
    });
  });
});
