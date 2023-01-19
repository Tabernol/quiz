var dur = (${requestScope.duration});

window.onload = function () {
    timer(dur);
}

function timer(min) {
    var minute = min
    // var sec = 01;
    setInterval(function () {
        dur = minute;
        document.getElementById("timer").innerHTML = minute;
        minute--;
        if (minute == 0) {
            minute = 5;
        }
    }, 1000);
}

function getCurrentTime() {
    return dur;
}


// function timer(min) {
//     var minute = min
//     var sec = 01;
//     setInterval(function () {
//         document.getElementById("timer").innerHTML = minute + ":" + sec;
//         sec--;
//         if (sec == 00) {
//             minute--;
//             sec = 60;
//
//             if (minute == 0) {
//                 minute = 5;
//             }
//         }
//     }, 1000);
// }
