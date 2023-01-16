window.onload = function () {
    timer(${requestScope.duration});

}

function timer(min) {
    var minute = min
    var sec = 60;
    setInterval(function () {
        document.getElementById("timer").innerHTML = minute + ":" + sec;
        sec--;

        if (sec == 00) {
            minute--;
            sec = 60;

            if (minute == 0) {
                minute = 5;
            }
        }
    }, 1000);
}
