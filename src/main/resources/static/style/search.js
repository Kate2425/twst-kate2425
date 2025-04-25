window.addEventListener("load", function () {
  $(function () {
    /* アコーディオンを開く */
    $('[data-accordion="title"]').on("click", function () {
      $(this)
        .closest('[data-accordion="box"]')
        .find('[data-accordion="item"]')
        .slideToggle();
      $(this).toggleClass("is-open");
    });
  });

  const magicChecks1 = $('[name="magicChecks1"]');
  const include1 = $('[name="include1"]');
  const include11 = $("#include11");
  const include1ErrorMsg = "マジック属性１のラジオボタンを選択してください。";
  const magicChecks1ErrorMsg =
    "マジック属性１のチェックボックスを選択してください。";

  //チェックボックスの状態変化時に処理する
  magicChecks1.on("change", () => {
    //チェック済チェックボックス数をカウント
    const isMagicCount = magicChecks1.filter(":checked");
    // ラジオボタンの状態を取得
    var result = $('input[name="include1"]:checked').val();
    //チェックボックスのカウントが1以上
    if (isMagicCount.length > 0) {
      //ラジオボタンを活性化する
      include1.attr("disabled", false);
      //ラジオボタンが未選択の場合はラジオボタンにrequired属性を付与する
      if (result != "include" && result != "exclude") {
        magicChecks1.attr("required", true);
      } else {
        magicChecks1.attr("required", false);
      }
      //チェックボックスのカウントが0かつラジオボタンが選択済の場合、ラジオボタンを非活性にする
    } else if (
      isMagicCount.length == 0 &&
      (result == "include" || result == "exclude")
    ) {
      include1.attr("disabled", true);
    } else {
      magicChecks1.attr("required", false);
    }
  });

  //ラジオボタンの状態変化時に処理する
  include1.on("change", () => {
    //チェック済チェックボックス数をカウント
    const isMagicCount = magicChecks1.filter(":checked");
    // ラジオボタンの状態を取得
    var result = $('input[name="include1"]:checked').val();
    console.log("result:" + result);
    console.log("count:" + isMagicCount.length);

    // チェックボックス数が1以上のとき、ラジオボタンとチェックボックスからrequired属性を外す
    if (isMagicCount.length > 0) {
      include11.attr("required", false);

      magicChecks1.attr("required", false);
    } else {
      //チェックボックス数が1以下のとき、ラジオボタンにrequired属性を付与する
      include11.attr("required", true);
    }
  });

  const tableCheckAll = document.getElementById("tableChecksAll");
  const tableChecks = document.querySelectorAll(".tableChecks");

  // 全て選択のチェックボックスがクリックされた時
  tableCheckAll.addEventListener("click", () => {
    for (val of tableChecks) {
      tableCheckAll.checked == true
        ? (val.checked = true)
        : (val.checked = false);
    }
  });

  // 個別のチェックボックスがクリックされた時
  tableChecks.forEach((element) => {
    element.addEventListener("click", () => {
      // チェックが1つでも外された時
      if (element.checked == false) {
        tableCheckAll.checked = false;
      }

      // 全てにチェックがされた時
      if (
        document.querySelectorAll(".tableChecks:checked").length ==
        tableChecks.length
      ) {
        tableCheckAll.checked = true;
      }
    });
  });

  const checkboxes = $('[name="tableNameChecks"]');

  //チェックボックスの状態変化時に処理する
  checkboxes.on("change", () => {
    //チェック済チェックボックス数をカウント
    const isCheckedCount = checkboxes.filter(":checked");

    isCheckedCount.length > 0
      ? //カウントが1以上の場合は全チェックボックスのrequired属性を削除する
        checkboxes.attr("required", false)
      : //カウントが0の場合は全チェックボックスにrequired属性を付与する
        checkboxes.attr("required", true);
  });

  const searchBtn = $("#search");
  const tableNameErrorMsg = "1つ以上を選択してください。";

  // 送信ボタン押下時に処理を開始する
  searchBtn.on("click", () => {
    // invalidイベント発火
    checkboxes.on("invalid", (e) => {
      // 値が正常か無効かを判定する
      const isInvalid = e.target.validity.valueMissing;

      isInvalid
        ? // 無効の場合はエラーメッセージを設定する
          e.target.setCustomValidity(tableNameErrorMsg)
        : // 正常な場合はエラーメッセージを空にする
          e.target.setCustomValidity("");
    });
    // invalidイベント発火
    magicChecks1.on("invalid", (e) => {
      // 値が正常か無効かを判定する
      const isInvalid = e.target.validity.valueMissing;

      isInvalid
        ? // 無効の場合はエラーメッセージを設定する
          e.target.setCustomValidity(include1ErrorMsg)
        : // 正常な場合はエラーメッセージを空にする
          e.target.setCustomValidity("");
    });
    // invalidイベント発火
    include11.on("invalid", (e) => {
      // 値が正常か無効かを判定する
      const isInvalid = e.target.validity.valueMissing;
      isInvalid
        ? // 無効の場合はエラーメッセージを設定する
          e.target.setCustomValidity(magicChecks1ErrorMsg)
        : // 正常な場合はエラーメッセージを空にする
          e.target.setCustomValidity();
    });
  });
});
