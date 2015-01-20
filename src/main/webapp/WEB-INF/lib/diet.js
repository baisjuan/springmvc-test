var dietsList;

$('#suggest_diets').click(function () {
    if (($('#breakfast_percentage').val() != "")
        & ($('#lunch_percentage').val() != "")
        & ($('#supper_percentage').val() != "")
        & ($('#dinner_percentage').val() != ""))
        $.ajax({
            type: 'POST',
            url: 'diet/generate',
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify({
                "calories": parseInt($('#diet_calories').val()),
                "breakfastPerc": parseInt($('#breakfast_percentage').val()),
                "lunchPerc": parseInt($('#lunch_percentage').val()),
                "supperPerc": parseInt($('#supper_percentage').val()),
                "dinnerPerc": parseInt($('#dinner_percentage').val())
            }),
            success: function (data) {
                dietsList = data.list;
                // TODO: list diets in UI
            }
        })
    else
        $.ajax({
            type: 'GET',
            url: 'diet/generate/' + $('#diet_calories').val(),
            success: function (data) {
                dietsList = data.list;
                // TODO: list diets in UI
            }
        })

})

$('#add_diet').click(function () {
    //diet_option should be select or radio buttons
    //    var diet = dietsList[$('#diet_option').val()];
    var diet = dietsList[2];
    $.ajax({
        type: 'POST',
        url: 'diet',
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify({
            "name": $('#diet_name').val(),
            "lunch": diet.lunch,
            "dinner": diet.dinner,
            "breakfast": diet.breakfast,
            "supper": diet.supper
        }),
        success: print
    })
})

$('#delete_diet').click(function () {
    $.ajax({
        type: 'DELETE',
        url: 'diet/' + $('#diet_name').val(),
        success: print
    });
})

$('#list_diets').click(function () {
    $.ajax({
        type: 'GET',
        url: 'diet',
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
              //TODO: list diets from the DB
        }
    })
})
