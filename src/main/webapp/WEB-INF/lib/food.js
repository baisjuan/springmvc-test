var list = '';

$.ajax({
    type: 'GET',
    url: 'food',
    success: function(data) {

        for (var i = 0; i < data.length; i++) {

            $('#foodsTableBody').append('<tr>');
                $('#foodsTableBody').append('<td>' + i + '</td>');
                $('#foodsTableBody').append('<td>' + data[i].name + '</td>');
                $('#foodsTableBody').append('<td>' + data[i].calories + '</td>');
                $('#foodsTableBody').append('<td>' + data[i].breakfast + '</td>');
                $('#foodsTableBody').append('<td>' + data[i].lunch + '</td>');
                $('#foodsTableBody').append('<td>' + data[i].supper + '</td>');
                $('#foodsTableBody').append('<td>' + data[i].dinner + '</td>');
            $('#foodsTableBody').append('</tr>');
        }

        $("#foodsTable").show();

    }
});

$('#add_new_food').click(function(){
    $.ajax({
        type: 'POST',
        url: 'food',
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify({
            "name":decodeURIComponent($('#food_name_add').val()),
            "calories":$('#calories').val(),
            "lunch":$('#lunch').attr("value"),
            "dinner":$('#dinner').attr("value"),
            "breakfast":$('#breakfast').attr("value"),
            "supper":$('#supper').attr("value")
        }),
        success: function(e){alert(e);}
    });
});