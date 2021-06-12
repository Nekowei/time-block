var currentDay = 0
var ctx = ""

$(function () {

    $.ajax("/user")
        .done(function (data) {
            $("#username").text(data)
        })
        .fail(function (xhr) {
            if (xhr.status == 401) {
                location = "/login"
            }
        })

    loadType()

    $("#type-add-color").change(e => {
        $("#type-add-button").attr("style", "background:" + $(e.target).val())
    })
    $("#type-add-select").change(e => {
        let color = $("#type-add-select option:selected").attr("color")
        $("#type-add-button").attr("style", "background:" + color)
        $("#type-add-color").val(color)
    })

    $("#type-add-div").hide()

    $("#type-add-submit").submit(e => {
        $("#type-add-button").attr("disabled", "disabled")
        e.preventDefault()
        let t = $(e.target)
        let formData = t.serializeArray()
        $.post(t.attr("action"), formData, () => { }, "json")
            .always(function (dx, status, xe) {
                $("#type-add-button").removeAttr("disabled")
                loadType()
            })
    })

    changeDay(currentDay)

})

function loadType() {
    $.get(ctx + "/type/tree", data => {
        $("#type-div").html("")
        $("#type-add-select").html("<option value='' color='#fff'>-</option>")
        data.forEach(updateTypeUI)

        $(".type-button").click(e => {
            let t = $(e.target)
            $("#current-type").val(t.val())
            $("#current-type").attr("style", t.attr("style"))
            $("#current-type").html(t.html())
        })
        $(".type-button-edit").click(e => {
            let t = $(e.target)
            console.log(t.val())
        })
        $(".type-button-delete").click(e => {
            $(".type-button-delete").attr("disabled", "disabled")
            $.post(ctx + "/type/delete", { id: $(e.target).val() }, () => { }, "json")
                .done(function (data, status, xhr) {
                })
                .fail(function (xhr, status, e) {
                })
                .always(function (dx, status, xe) {
                    loadType()
                })
        })
        $(".type-button-edit").hide()
        $(".type-button-delete").hide()
    })
}

// for each type
function updateTypeUI(t) {
    $("#type-div").append("<div id='type-" + t.id + "' class='type-div'></div>")
    let tid = "#type-" + t.id
    $(tid)
        .append("<div class='type major'>"
            + "<button value='" + t.id + "' style='background:" + t.color + "' class='type-button type-block'>" + t.name + "</button>"
            + "<button value='" + t.id + "' style='background:" + t.color + "' class='type-button-edit'>&#128296;</button>"
            + "<button value='" + t.id + "' style='background:" + t.color + "' class='type-button-delete'>&#10060;</button>"
            + "</div>")
    t.detail.forEach(d => {
        $(tid)
            .append("<div class='type minor'>"
                + "<button value='" + d.id + "' style='background:" + d.color + "' class='type-button type-block'>" + d.name + "</button>"
                + "<button value='" + d.id + "' style='background:" + d.color + "' class='type-button-edit'>&#128296;</button>"
                + "<button value='" + d.id + "' style='background:" + d.color + "' class='type-button-delete'>&#10060;</button>"
                + "</div>")
    })

    // fill the select list by the way
    $("#type-add-select").append("<option value='" + t.id + "' color='" + t.color + "'>" + t.name + "</option>")
}

function changeDay(day) {
    currentDay += day
    $(".change-button").attr("disabled", "disabled")
    $.get(ctx + "/block/date", { day: currentDay }, d => {
        $("#current-date").text(d)
    })
    $.get(ctx + "/block/list", { day: currentDay }, data => {
        $(".change-button").removeAttr("disabled")
        $("#time-list").html("")
        for (const [hour, list] of Object.entries(data)) {
            let div = "<div class='time-list-hour'>"
                + "<button class='time-list-hour-button'>" + hour + "</button>"
            for (let i = 0; i < list.length; i++) {
                const m = list[i];
                div += "<button class='time-list-minute-button type-block' style='background:" + m.color
                    + "' start='" + m.startTime + "' end='" + m.endTime + "'>" + m.name + "</button>"
            }
            div += "</div>"
            $("#time-list").append(div)
        }

        $(".time-list-hour-button").click(onHourButtonClick)

        $(".time-list-minute-button").click(onMinuteButtonClick)

    })
}

function onHourButtonClick(e) {
    if ($("#current-type").val()) {
        let t = $(e.target)
        $.post(ctx + "/block/save/all", {
            typeId: $("#current-type").val(),
            recordDate: $("#current-date").text(),
            hour: t.text()
        }, () => { }, "json")
            .always(function (dx, status, xe) {
                let n1 = t.next()
                n1.text($("#current-type").text())
                n1.attr("style", $("#current-type").attr("style"))

                let n2 = t.next().next()
                n2.text($("#current-type").text())
                n2.attr("style", $("#current-type").attr("style"))

                let n3 = t.next().next().next()
                n3.text($("#current-type").text())
                n3.attr("style", $("#current-type").attr("style"))

                let n4 = t.next().next().next().next()
                n4.text($("#current-type").text())
                n4.attr("style", $("#current-type").attr("style"))
            })
    }
}

function onMinuteButtonClick(e) {
    if ($("#current-type").val()) {
        let t = $(e.target)
        $.post(ctx + "/block/save", {
            typeId: $("#current-type").val(),
            recordDate: $("#current-date").text(),
            startTime: t.attr("start"),
            endTime: t.attr("end")
        }, () => { }, "json")
            .always(function (dx, status, xe) {
                t.text($("#current-type").text())
                t.attr("style", $("#current-type").attr("style"))
            })
    }
}