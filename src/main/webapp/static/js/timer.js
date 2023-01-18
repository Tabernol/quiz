window.onload = function () {
    timer(${requestScope.duration});

}

let t;


function timer(min) {
    var minute = min
    var sec = 60;
    setInterval(function () {
        document.getElementById("timer").innerHTML = minute + ":" + sec;
        minute--;
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
