$(document).ajaxError(function () {
    $(".err").text("Triggered ajaxError handler.");
});
loadBooks();

function loadBooks() {
    $("#tblAllBooks tbody").empty();
    $.ajax({
        url: '/api/findAll',
        success: function (data) {
            data.forEach(function (book, i) {
                var a = "";
                book.authors.forEach(function (author, j) {
                    a += "<div>" + author.firstName + " " + author.secondName + "</div>";
                })
                $("#tblAllBooks tbody").append(`<tr>
                                                  <td>` + (i + 1) + `</td>
                                                  <td>` + book.name + `</td>
                                                  <td>` + book.genre + `</td>
                                                  <td>` + book.pageCount + `</td>
                                                  <td>` + a + `</td>
                                                  <td>
                                                        <div><a href="update?id=${book.id}">Редактировать</a></div>
                                                      <div><button id="` + book.id + `" onClick="deleteBook(this.id)">Удалить</button></div>
                                                  </td>
                                             </tr>`);
            });
        }
    });
}

function deleteBook(id) {
    console.log(id);

    $.ajax({
        url: '/api/deleteById',
        method: 'POST',
        data: JSON.stringify({
            id: id
        }),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        beforeSend: function () {
            $("#tblAllBooks tbody").empty();
        },
        complete: function (data) {
            if (data.status == 200) {
            }
            else if (data.status == 404) {
                alert("Книга не найдена");
            }
            else {
                alert("Неизвестная ошибка");
            }
            loadBooks();
        }
    });
}