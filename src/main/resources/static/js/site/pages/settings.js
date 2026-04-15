$(function () {
    let timeout = null;
    $(".settings__input").on("input", function () {
        clearTimeout(timeout);

        timeout = setTimeout(() => {
            this.form.submit();
        }, 2000);
    })
})