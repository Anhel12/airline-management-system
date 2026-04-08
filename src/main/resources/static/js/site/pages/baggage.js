$(function(){
    $('.dropdown-menu__item-top').on('click', function(){
        $(this).toggleClass('dropdown-menu__item-top--show');
        $(this).closest('.dropdown-menu__item').find('.dropdown-menu__content').toggleClass('dropdown-menu__content--show');
    })
})