$(function (){

    flatpickr(".date", {
        altInput: true,
        altFormat: "d.m.Y",
        dateFormat: "Y-m-d",
        locale: "ru"
    });


    $('.add, .overlay').on('click', function(e){
        e.preventDefault();

        $('.main__add-entity').toggleClass('main__add-entity--show');
        $('.overlay').toggleClass('overlay--show');
    })

    $('.table__main-form').on('submit', function (e){
        e.preventDefault();

        let items = [];

        $('.table__main-list').each(function () {
            let item = {
                id: $(this).find('[name$=".id"]').val(),
                bookingNumber: $(this).find('[name$=".bookingNumber"]').val(),
                bookingDate: $(this).find('[name$=".bookingDate"]').val(),
                flight_id: $(this).find('[name$=".flight_id"]').val(),
                totalAmount: $(this).find('[name$=".totalAmount"]').val(),
                status: $(this).find('[name$=".status"]').val()
            };

            items.push(item);
        })

        $.ajax({
            url: '/api/admin/booking',
            type: 'PATCH',
            contentType: 'application/json',
            data: JSON.stringify(items),
            headers: {
                "X-XSRF-TOKEN": $.cookie('XSRF-TOKEN')
            },
            xhrFields: {
                withCredentials: true
            },

            success: function (res) {
                location.reload();
            },

            error: function (xhr) {
                const errors = xhr.responseJSON;
                const box = $('.error');

                showErrors(errors, box)
            }

        })
    })

    $('.add-entity__form').on('submit', function (e){
        e.preventDefault();

        const data = {
            bookingNumber: $(this).find('[name="bookingNumber"]').val(),
            bookingDate: $(this).find('[name="bookingDate"]').val(),
            flight_id: $(this).find('[name="flightNumber"]').val(),
            totalAmount: $(this).find('[name="totalAmount"]').val(),
            status: $(this).find('[name="status"]').val()
        }
        console.log(data);
        $.ajax({
            url: '/api/admin/booking',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            headers: {
                "X-XSRF-TOKEN": $.cookie('XSRF-TOKEN')
            },

            xhrFields: {
                withCredentials: true
            },

            success: function (res) {
                location.reload();
            },

            error: function (xhr) {
                const errors = xhr.responseJSON;
                const box = $('.error');

                showErrors(errors, box)
            }

        })
    })

    function showErrors(errors, box){
        box.html("");
        if(Array.isArray(errors)){
            errors.forEach(err => {
                box.append(`<p>${err}</p>`)
            });
        } else {
            box.append(`<p>${errors?.errors}</p>`);
        }

    }
})