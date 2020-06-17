$("#b_saveBook").click(function () {
    $("#i_resultLabel").empty().removeClass("cl_resultLabel_suc").removeClass("cl_resultLabel_err");

    var name = $("#i_name").val();
    var genre = $("#i_genre").val();
    var pageCount = $("#i_pageCount").val();
    var authorName1 = $("#i_a1_first_name").val();
    var authorSecondName1 = $("#i_a1_second_name").val();

    if ((name == "") || (genre == "") || (pageCount == "")) {
        $("#i_resultLabel").empty().removeClass("cl_resultLabel_suc").removeClass("cl_resultLabel_err");
        $("#i_resultLabel").append("Не все обязательные поля заполнены").addClass("cl_resultLabel_err");
        return;
    }

    $.ajax({
        url: '/api/create',
        method: 'POST',
        data: JSON.stringify({
            name: name,
            genre: genre,
            pageCount: pageCount,
            authors: [{firstName: authorName1, secondName: authorSecondName1}]
        }),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        beforeSend: function () {
            $("#i_resultLabel").empty().removeClass("cl_resultLabel_suc").removeClass("cl_resultLabel_err");
        },
        complete: function (data) {
            if (data.status == 200) {
                $("#i_resultLabel").append("Книга создана, id=" + data.responseJSON.id).addClass("cl_resultLabel_suc");
            } else if (data.status == 409) {
                $("#i_resultLabel").append("Книга с названием '" + data.responseJSON.name + "' уже существует").addClass("cl_resultLabel_err");
            } else {
                $("#i_resultLabel").append("Неизвестная ошибка").addClass("cl_resultLabel_err");
            }
        }
    });
})