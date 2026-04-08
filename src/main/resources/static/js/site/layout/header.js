$(function(){

    $('.login, .overlay, .auth-panel__top svg').on('click', function(e){
        e.preventDefault();

        if($('.auth-panel__login').hasClass('hidden')){
            $('.auth-panel__signup').addClass('hidden');
            $('.auth-panel__login').removeClass('hidden');
        }
        
        $('.auth-panel').toggleClass('auth-panel--show');
        $('.overlay').toggleClass('overlay--show');
    })

    $('.reg').on('click', function(e){
        e.preventDefault();
        $('.auth-panel__login').addClass('hidden');
        $('.auth-panel__signup').removeClass('hidden');
    })
    $('.log').on('click', function(e){
        e.preventDefault();
        $('.auth-panel__signup').addClass('hidden');
        $('.auth-panel__login').removeClass('hidden');
    })

    $('.account').on('click', function(){
        $('.settings-menu').toggleClass('settings-menu--show');
    })

})