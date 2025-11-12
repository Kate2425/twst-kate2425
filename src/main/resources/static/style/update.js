window.addEventListener('load', function () {
  // 初期処理
  $(function () {
    // 全てのチェックボックスをOFF
    $('input[type="checkbox"]').prop('checked', false);
    console.log('initialize');
  });

  const tableViewName = $('input[name="tableViewName"]').val();
  console.log('tableViewName:%o', tableViewName);

  //表示処理
  $(function () {
    //チェックボックスがクリックされたとき
    $('input[type="checkbox"]').change(function () {
      let elementId = '#' + $(this).attr('id').replace('input', 'select');
      console.log('elementId:%o', elementId);
      if ($(this).prop('checked')) {
        //チェックあり
        $(elementId).show();
        console.log('show');
      } else {
        //チェックなし
        $(elementId).hide();
        console.log('hide');
      }
    });
  });

  // 未チェック取得処理
  function getCheckboxes() {
    let checkboxes = [];
    $('input[type="checkbox"]:checked').each(function (index, elm) {
      console.log(
        '$(input[type="checkbox"]:checked):%o',
        $('input[type="checkbox"]:checked')
      );
      console.log('elm:%o', elm);
      const baseElementName = $(elm).attr('name').replace('input', '');
      console.log('baseElementName:%o', baseElementName);

      const elementName =
        baseElementName.substring(0, 1).toLowerCase() +
        baseElementName.substring(1);
      console.log('elementName:%o', elementName);

      const elementId = $(elm).attr('id').replace('input', 'select');
      console.log('elementId:%o', elementId);

      const selectValue = $('#' + elementId).val();
      console.log('selectValue:%o', selectValue);

      let temp = {};
      temp.name = elementName;
      temp.val = selectValue;
      checkboxes[index] = temp;
    });
    console.log('checkboxes:%o', checkboxes);
    return checkboxes;
  }

  // 送信処理
  $(function () {
    $('button').click(function (e) {
      if ($(this).attr('id') == 'modal') {
        return;
      }

      e.preventDefault(); // ボタンのデフォルト送信を無効化
      characterViewName = $(this)
        .parent()
        .parent()
        .find('input[name="characterViewName"]')
        .val();
      console.log('characterViewName:%o', characterViewName);
      let message =
        tableViewName + ' ' + characterViewName + 'を更新しました。';

      const formName = $(this).attr('id').replace('submit', 'cardForm');
      console.log('formName:%o', formName);
      const form = $(this)
        .parent()
        .parent()
        .find('#' + formName);
      console.log('form:%o', form);
      const checkboxes = getCheckboxes();

      // 既存の form を削除（2重送信防止）
      $(this).parent().parent().find('.selectbox').remove();
      $(this).parent().parent().find('.textbox').remove();

      // チェックボックスの値を hidden として追加
      $.each(checkboxes, function (index) {
        $('<input>')
          .attr({
            type: 'hidden',
            name: checkboxes[index].name,
            value: checkboxes[index].val,
          })
          // .addClass('auto-generated') // 自動生成フラグ
          .appendTo(form);
      });
      console.log('append-form:%o', form);

      // フォーム送信
      form.submit();
      $('.modal-body').find('p').remove(); //モーダルウィンドウの表示前に<p>タグを削除する
      $('.modal-body').append('<p>' + message + '</p>'); //<p>タグの追加。
      $('#successMsg').modal('show'); //モーダルウィンドウを表示する
    });
  });
});
