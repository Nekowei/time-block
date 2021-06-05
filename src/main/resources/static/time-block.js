$(function () {
    loadType()

    $("#type-add-color").change(e => {
        $("#type-add-button").attr("style", "background:" + $(e.target).val())
    })

    $("#type-add").hide()

    $("#type-add-submit").submit(e => {
        e.preventDefault()
        let t = $(e.target)
        let formData = t.serializeArray()
        $.post(t.attr("action"), formData, b => {
            console.log(b)
            if (b.status == 200) {
                loadType()
            } else {

            }
        }, "json")
    });
})

function loadType() {
    $.get("type/tree", data => {
        data.forEach(updateTypeUI)
    })
}

function updateTypeUI(t) {
    $("#type-list").append("<div>")
    $("#type-list").append(
        "<button id='" + t.id + "' style='background:" + t.color + "' class='type-button'>" + t.name + "</button>")
    t.detail.forEach(d => {
        $("#type-list").append(
            "<button id='" + d.id + "' style='background:" + d.color + "' class='type-button'>" + d.name + "</button>")
    })
    $("#type-list").append("</div>")

    $("#type-add-select").append("<option value='" + t.id + "'>" + t.name + "</option>")
}