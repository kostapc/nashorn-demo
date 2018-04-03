

function cmdTime(username, context) {
    var userdata = context.load(username);
    var timestamp = null;
    if(userdata == null) {
        timestamp = new Date().getTime();
        context.save(username,{
            "timestamp": timestamp
        });
        print("timestamp saved for "+username+"; ask me later to get elapsed time;");
        context.send("this is first call. I'm saved your time");
        return;
    } else {
        timestamp = userdata.timestamp;
    }
    timestamp = new Date().getTime() - timestamp;
    timestamp = timestamp/1000;
    print(timestamp+"seconds passed since "+username+" last ask");
    context.send(timestamp+" seconds passed since your last ask");
    timestamp = new Date().getTime();
    context.save(username,{
        "timestamp": timestamp
    });
}

function cmdCities(username, context) {
    context.send("game not ready");
}

function userMessage(msg, context) {
    context.send("you said: "+msg);
}