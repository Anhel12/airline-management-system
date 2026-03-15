$(function(){
    let originalSize;
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
    })

    $(".spec-panel__box-item--medium-font-size").on("click", function(){
        if(!originalSize) {
            originalSize = parseFloat($("html").css("font-size"));
        }

        $("html").css("font-size", originalSize * 1.15 + "px");
    })

    $(".spec-panel__box-item--big-font-size").on("click", function(){
        if(!originalSize) {
            originalSize = parseFloat($("html").css("font-size"));
        }

        $("html").css("font-size", originalSize * 1.23 + "px");
    })
// ТУт леттер спасинг
    $(".spec-panel__box-item--standart-letter-spacing").on("click", function(){
        $("html").css("letter-spacing", 0 + "rem");
    })
    $(".spec-panel__box-item--medium-letter-spacing").on("click", function(){
        $("html").css("letter-spacing", .125 + "rem");
    })
    $(".spec-panel__box-item--big-letter-spacing").on("click", function(){
        $("html").css("letter-spacing", .25 + "rem");
    })
// Тут лайн хайт
    $(".spec-panel__box-item--standart-line-height").on("click", function(){
        $("body").css("line-height", 1);
    })
    $(".spec-panel__box-item--medium-line-height").on("click", function(){
        $("body").css("line-height", 1.2);
    })
    $(".spec-panel__box-item--big-line-height").on("click", function(){
        $("body").css("line-height", 1.4);
    })
// Тут цвет
    $(".spec-panel__box-item--standart-color").on("click", function(){
        $("body").css("background", "");
        $("body").css("color", "");

        $(".second").css("background", "");
        $(".second h3, .second .main__item-value, .second").css("color", "");
        $(".second .main__item-value svg path, .button.second svg path").css("fill", "");

        $(".third a, .table__main-item").css("background", "");
        $(".third a, .table__main-item").css("color", "");
        $(".header__nav-glasses-btn svg path").css("fill", "");
    
        $(".header__title").css("color", "");
        $(".header-inner, .main__search-inner, .stripe").css("border-bottom", "");

    })
    $(".spec-panel__box-item--black-white-color").on("click", function(){
        $("body").css("background", "#fff");
        $("body").css("color", "#000");

        $(".second").css("background", "#000");
        $(".second h3, .second .main__item-value, .second").css("color", "#fff");
        $(".second .main__item-value svg path, .button.second svg path").css("fill", "#fff");

        $(".third a, .table__main-item").css("background", "#fff");
        $(".third a, .table__main-item").css("color", "#000");
        $(".header__nav-glasses-btn svg path").css("fill", "#000");

        $(".header__title").css("color", "#000");
        $(".header-inner, .main__search-inner, .stripe").css("border-bottom", "0.5px solid #000");

    })
    $(".spec-panel__box-item--white-black-color").on("click", function(){
        $("body").css("background", "#000");
        $("body").css("color", "#fff");

        $(".second").css("background", "#fff");
        $(".second h3, .second .main__item-value, .second").css("color", "#000");
        $(".second .main__item-value svg path, .button.second svg path").css("fill", "#000");

        $(".third a, .table__main-item").css("background", "#fff");
        $(".third a, .table__main-item").css("color", "#000");
        $(".header__nav-glasses-btn svg path").css("fill", "#fff");
    
        $(".header__title").css("color", "#fff");
        $(".header-inner, .main__search-inner, .stripe").css("border-bottom", "0.5px solid #fff");

    })
    $(".spec-panel__box-item--black-blue-color").on("click", function(){
        $("body").css("background", "#0af");
        $("body").css("color", "#000");

        $(".second").css("background", "#000");
        $(".second h3, .second .main__item-value, .second").css("color", "#0af");
        $(".second .main__item-value svg path, .button.second svg path").css("fill", "#0af");

        $(".third a").css("background", "#0af");
        $(".third a").css("color", "#000");
        $(".header__nav-glasses-btn svg path").css("fill", "#000");

        $(".header__title").css("color", "#000");
        $(".header-inner, .main__search-inner, .stripe").css("border-bottom", "0.5px solid #000");
    })
//  Тут изображения
    $(".spec-panel__box-item--img-off").on("click", function(){
        $(".main__stats-content-img svg").hide();
    })
    $(".spec-panel__box-item--img-on").on("click", function(){
        $(".main__stats-content-img svg").show();
    })

})