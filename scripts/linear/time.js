print("what is bot?: "+JSON.stringify(bot));

var username = bot.username;
var userdata = bot.load(username);
var timestamp = null;
if(userdata == null) {
    timestamp = new Date().getTime();
    bot.save(username,{
        "timestamp": timestamp
    });
    print("timestamp saved for "+username+"; ask me later to get elapsed time;");
    bot.send("this is first call. I'm saved your time");
} else {
    timestamp = userdata.timestamp;
    timestamp = new Date().getTime() - timestamp;
    timestamp = timestamp/1000;
    print(timestamp+"seconds passed since "+username+" last ask");
    bot.send(timestamp+" seconds passed since your last ask");
    timestamp = new Date().getTime() - timestamp;
    bot.save(username,context);
}