let isTimerRun = true;

function getUserAnswer(params) {
    let result = "";
    for (i = 0; i < params.length; i++) {
        result += "&res=" + params[i].value
        if (params[i].checked) {
            result += "&res=on"
        } else {
            result += "&res=off"
        }
    }
    return result;
}

function loadQuestionAndAnswer(id_question, number_question, res) {
    console.log("===question number" + number_question + "=============");

    let result = getUserAnswer(res)
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("text_question").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "get_text_question?id_question=" + id_question +
        "&number_question=" + number_question +
        result, true);
    xhttp.send();

    if ((${sessionScope.size}).toString() === number_question) {
        isTimerRun = false;
        document.getElementById("timer").innerHTML = null;
        // finishTest();
    }
}

function timer(min) {
    let minute = --min;
    let sec = 59;
    setInterval(function () {
        document.getElementById("timer").innerHTML = "<h1>" + minute + " : " + sec + "</h1>";
        if (isTimerRun) {
            sec--;
            if (sec == 0) {
                if (minute != 0) {
                    minute--;
                    sec = 60;
                }
                if (minute == 0 && sec == 0) {
                    //  isTimerRun = false;
                    document.getElementById("timer").innerHTML = null;
                    finishTest();
                }
            }
        }
    }, 1000);
}

function finishTest() {
    document.location.href = "${pageContext.request.contextPath}/finish_test"
}

// test Timer
