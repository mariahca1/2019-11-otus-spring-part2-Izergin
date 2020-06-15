var bookId = new URL(window.location.href).searchParams.get("id");
loadBookData(bookId);

function deleteAuthor(bookId, authorNum, authorId) {
    $.ajax({
        url: '/api/author/delete',
        method: 'POST',
        data: JSON.stringify({
            bookId: bookId,
            authorNum: authorNum,
            authorId: authorId
        }),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        complete: function (data) {
            if (data.status == 200) {
                loadBookData(bookId);
            } else {
                alert("Неизвестная ошибка " + data.status);
            }
        }
    });
}

function loadBookData(id) {
    $.ajax({
        url: '/api/findById',
        method: 'POST',
        data: JSON.stringify({
            id: bookId
        }),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        beforeSend: function () {
            $("#tblAuthors tbody").empty();
        },
        complete: function (data) {
            $("#i_id").val(data.responseJSON.id);
            $("#i_name").val(data.responseJSON.name);
            $("#i_genre").val(data.responseJSON.genre);
            $("#i_pageCount").val(data.responseJSON.pageCount);

            data.responseJSON.authors.forEach(function (author, i) {
                $("#tblAuthors tbody").append(`<tr class="author_row">
                                              <td>` + (i + 1) + `</td>
                                              <td>` + author.firstName + `</td>
                                              <td>` + author.secondName + `</td>
                                              <td>
                                                 <div><button onClick="deleteAuthor('` + bookId + `',` + i + `,'` + author.id + `')">Удалить</button></div>
                                              </td>
                                         </tr>`);
            })
        }
    });
}

$("#b_saveBook").click(function () {
    $("#i_resultLabel").empty().removeClass("cl_resultLabel_suc").removeClass("cl_resultLabel_err");

    var id = $("#i_id").val();
    var name = $("#i_name").val();
    var genre = $("#i_genre").val();
    var pageCount = $("#i_pageCount").val();

    if ((name == "") || (genre == "") || (pageCount == "")) {
        $("#i_resultLabel").empty().removeClass("cl_resultLabel_suc").removeClass("cl_resultLabel_err");
        $("#i_resultLabel").append("Не все обязательные поля заполнены").addClass("cl_resultLabel_err");
        return;
    }

    $.ajax({
        url: '/api/book/update',
        method: 'POST',
        data: JSON.stringify({
            id: id,
            name: name,
            genre: genre,
            pageCount: pageCount
        }),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        beforeSend: function () {
            $("#i_resultLabel").empty().removeClass("cl_resultLabel_suc").removeClass("cl_resultLabel_err");
        },
        complete: function (data) {
            if (data.status == 200) {
                $("#i_resultLabel").append("Изменения сохранены id=" + data.responseJSON.id).addClass("cl_resultLabel_suc");
            } else {
                $("#i_resultLabel").append("Ошибка id=" + data.responseJSON.id).addClass("cl_resultLabel_err");
            }
        }
    });
})