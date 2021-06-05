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
        $.post(t.attr("action"), formData, () => { }, "json")
            .done(function (data, status, xhr) {
                loadType()
            })
            .fail(function (xhr, status, e) {
                alert(status)
            })
            .always(function (dx, status, xe) {
            })
    })

})

function loadType() {
    $.get("type/tree", data => {
        data.forEach(updateTypeUI)
        $(".type-button-edit").click(e => {
            let t = $(e.target)
            console.log(t.val())
        })
        $(".type-button-delete").click(e => {
            let t = $(e.target)
            console.log(t.val())
        })
        $(".type-button-edit").hide()
        $(".type-button-delete").hide()
    })
}

// for each type
function updateTypeUI(t) {
    $("#type-list").append("<div id='type-" + t.id + "' class='type-div'></div>")
    let tid = "#type-" + t.id
    $(tid)
        .append("<button value='" + t.id + "' style='background:" + t.color + "' class='type-button'>" + t.name + "</button>")
        .append("<button value='" + t.id + "' style='background:" + t.color + "' class='type-button-edit'>&#128296;</button>")
        .append("<button value='" + t.id + "' style='background:" + t.color + "' class='type-button-delete'>&#10060;</button>")
        .append("</br>")
    t.detail.forEach(d => {
        $(tid)
            .append("<button value='" + d.id + "' style='background:" + d.color + "' class='type-button'>" + d.name + "</button>")
            .append("<button value='" + d.id + "' style='background:" + d.color + "' class='type-button-edit'>&#128296;</button>")
            .append("<button value='" + d.id + "' style='background:" + d.color + "' class='type-button-delete'>&#10060;</button>")
    })
    $(tid).append("</br>")

    // fill the select list by the way
    $("#type-add-select").append("<option value='" + t.id + "'>" + t.name + "</option>")
}
