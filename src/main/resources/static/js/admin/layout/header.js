$(function(){
    let originalSize = parseFloat($('html').css('font-size'));

    function saveSettings(key, value) {
        localStorage.setItem(key, value);
    }

    function applySettings() {

        // FONT SIZE
        let fontSize = localStorage.getItem("fontSize");

        if(fontSize === "medium") {
            $(".spec-panel__box-item--medium-font-size").click();
        }

        if(fontSize === "big") {
            $(".spec-panel__box-item--big-font-size").click();
        }

        // LETTER SPACING
        let letterSpacing = localStorage.getItem("letterSpacing");

        if(letterSpacing === "medium") {
            $(".spec-panel__box-item--medium-letter-spacing").click();
        }

        if(letterSpacing === "big") {
            $(".spec-panel__box-item--big-letter-spacing").click();
        }

        // LINE HEIGHT
        let lineHeight = localStorage.getItem("lineHeight");

        if(lineHeight === "medium") {
            $(".spec-panel__box-item--medium-line-height").click();
        }

        if(lineHeight === "big") {
            $(".spec-panel__box-item--big-line-height").click();
        }

        // IMAGES
        let images = localStorage.getItem("images");

        if(images === "off") {
            $(".spec-panel__box-item--img-off").click();
        }

        let theme = localStorage.getItem("theme");

        if(theme == "black-white") {
            $(".spec-panel__box-item--black-white-color").click();
        }
        if(theme == "white-black") {
            $(".spec-panel__box-item--white-black-color").click();
        }
        if(theme == "blue") {
            $(".spec-panel__box-item--black-blue-color").click();

        }
    }

// Тут показ панели для слабовидящих
    $(".header__nav-glasses-btn").on("click", function(){
        $(".spec-panel").toggle();
    })

// Тут обводка для выбранных режимов
    $(".spec-panel__box-item").on("click", function(){
        let ul = $(this).closest("ul");
        ul.find(".spec-panel__box-item").removeClass("spec-panel__box-item--selected");
        
        $(this).addClass("spec-panel__box-item--selected");
    })
// Тут фон сайз
    $(".spec-panel__box-item--standart-font-size").on("click", function(){
        $("html").css("font-size", originalSize);

        saveSettings("fontSize", "standart");
    })

    $(".spec-panel__box-item--medium-font-size").on("click", function(){
        if(!originalSize) {
            originalSize = parseFloat($("html").css("font-size"));
        }

        $("html").css("font-size", originalSize * 1.4 + "px");

        saveSettings("fontSize", "medium");
    })

    $(".spec-panel__box-item--big-font-size").on("click", function(){
        if(!originalSize) {
            originalSize = parseFloat($("html").css("font-size"));
        }

        $("html").css("font-size", originalSize * 1.8 + "px");

        saveSettings("fontSize", "big");
    })
// ТУт леттер спасинг
    $(".spec-panel__box-item--standart-letter-spacing").on("click", function(){
        $("html").css("letter-spacing", 0 + "rem");

        saveSettings("letterSpacing", "standart");
    })
    $(".spec-panel__box-item--medium-letter-spacing").on("click", function(){
        $("html").css("letter-spacing", .125 + "rem");

        saveSettings("letterSpacing", "medium");
    })
    $(".spec-panel__box-item--big-letter-spacing").on("click", function(){
        $("html").css("letter-spacing", .25 + "rem");

        saveSettings("letterSpacing", "big");
    })
// Тут лайн хайт
    $(".spec-panel__box-item--standart-line-height").on("click", function(){
        $("body").css("line-height", 1);

        saveSettings("lineHeight", "standart");
    })
    $(".spec-panel__box-item--medium-line-height").on("click", function(){
        $("body").css("line-height", 1.2);

        saveSettings("lineHeight", "medium");
    })
    $(".spec-panel__box-item--big-line-height").on("click", function(){
        $("body").css("line-height", 1.4);

        saveSettings("lineHeight", "big");
    })
// Тут цвет
    $(".spec-panel__box-item--standart-color").on("click", function(){
        $("body").css("background", "");
        $("body").css("color", "");
        $(".aside__logo").css("color", "");

        $(".third").css("border", "");

        $(".second").css("background", "");
        $(".second h3, .second .main__item-value, .second").css("color", "");
        $(".second .main__item-value svg path, .button.second svg path").css("fill", "");

        $(".third a, .table__main-item, .main__add-entity").css("background", "");
        $(".third a, .table__main-item").css("color", "");
        $(".header__nav-glasses-btn svg path").css("fill", "");

        $(".header__title").css("color", "");
        $(".header-inner, .main__search-inner, .stripe").css("border-bottom", "");

        $(".table__main-input, .search__form, .search__input, .search__button, select").css("background", "");
        $(".table__main-item, .search__form").css("border", "");
        $(".table__main-item, .search__input, select").css("color", "");
        $(".search__form path").css("stroke", "");
        $(".search__form svg").css("fill", "");
        $(".table__top-filter path").css("fill", "");

        $(".main__search-inner, .header-inner, .stripe").css("border-bottom", "");

        saveSettings("theme", "standart");

    })
    $(".spec-panel__box-item--black-white-color").on("click", function(){
        $("body").css("background", "#F7F3D6");
        $("body").css("color", "#4D4B43");
        $(".aside__logo").css("color", "#F7F3D6");

        $(".third").css("border", "#F7F3D6");

        $(".second").css("background", "#4D4B43");
        $(".second h3, .second .main__item-value, .second").css("color", "#F7F3D6");
        $(".second .main__item-value svg path, .button.second svg path").css("fill", "#F7F3D6");

        $(".third a, .table__main-item, .main__add-entity").css("background", "#4D4B43");
        $(".third a, .table__main-item").css("color", "#F7F3D6");
        $(".header__nav-glasses-btn svg path").css("fill", "#4D4B43");

        $(".header__title").css("color", "#4D4B43");
        $(".header-inner, .main__search-inner, .stripe").css("border-bottom", "0.5px solid #F7F3D6");

        $(".table__main-input, .search__form, .search__input, .search__button, select").css("background", "#F7F3D6");
        $(".table__main-item, .search__form").css("border", "1px solid #4D4B43");
        $(".table__main-item, .search__input, select").css("color", "#4D4B43");
        $(".search__form path").css("stroke", "#4D4B43");
        $(".search__form svg").css("fill", "#F7F3D6");
        $(".table__top-filter path").css("fill", "#4D4B43");

        $(".main__search-inner, .header-inner, .stripe").css("border-bottom", "0.5px solid #4D4B43");

        saveSettings("theme", "black-white");
    })
    $(".spec-panel__box-item--white-black-color").on("click", function(){
        $("body").css("background", "#fff");
        $("body").css("color", "#000");

        $(".aside__logo").css("color", "#fff");

        $(".third").css("border", "#fff");

        $(".second").css("background", "#000");
        $(".second h3, .second .main__item-value, .second").css("color", "#fff");
        $(".second .main__item-value svg path, .button.second svg path").css("fill", "#fff");

        $(".third a, .table__main-item, .main__add-entity").css("background", "#000");
        $(".third a, .table__main-item").css("color", "#fff");
        $(".header__nav-glasses-btn svg path").css("fill", "#000");

        $(".header__title").css("color", "#000");
        $(".header-inner, .main__search-inner, .stripe").css("border-bottom", "0.5px solid #fff");

        $(".table__main-input, .search__form, .search__input, .search__button, select").css("background", "#fff");
        $(".table__main-item, .search__form").css("border", "1px solid #000");
        $(".table__main-item, .search__input, select").css("color", "#000");
        $(".search__form path").css("stroke", "#000");
        $(".search__form svg").css("fill", "#fff");
        $(".table__top-filter path").css("fill", "#000");

        $(".main__search-inner, .header-inner, .stripe").css("border-bottom", "0.5px solid #000");


        saveSettings("theme", "white-black");
    })
    $(".spec-panel__box-item--black-blue-color").on("click", function(){
        $("body").css("background", "#9DD1FF");
        $("body").css("color", "#063462");

        $(".aside__logo").css("color", "#9DD1FF");

        $(".third").css("border", "#9DD1FF");

        $(".second").css("background", "#063462");
        $(".second h3, .second .main__item-value, .second").css("color", "#9DD1FF");
        $(".second .main__item-value svg path, .button.second svg path").css("fill", "#9DD1FF");

        $(".third a, .table__main-item, .main__add-entity").css("background", "#063462");
        $(".third a, .table__main-item").css("color", "#9DD1FF");
        $(".header__nav-glasses-btn svg path").css("fill", "#063462");

        $(".header__title").css("color", "#063462");
        $(".header-inner, .main__search-inner, .stripe").css("border-bottom", "0.5px solid #9DD1FF");

        $(".table__main-input, .search__form, .search__input, .search__button, select").css("background", "#9DD1FF");
        $(".table__main-item, .search__form").css("border", "1px solid #063462");
        $(".table__main-item, .search__input, select").css("color", "#063462");
        $(".search__form path").css("stroke", "#063462");
        $(".search__form svg").css("fill", "#9DD1FF");
        $(".table__top-filter path").css("fill", "#063462");

        $(".main__search-inner, .header-inner, .stripe").css("border-bottom", "0.5px solid #063462");

        saveSettings("theme", "blue");
    })
//  Тут изображения
    $(".spec-panel__box-item--img-off").on("click", function(){
        $(".main__stats-content-img svg").hide();

        saveSettings("images", "standart");
    })
    $(".spec-panel__box-item--img-on").on("click", function(){
        $(".main__stats-content-img svg").show();

        saveSettings("images", "off");
    })

    applySettings();
})