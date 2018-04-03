

var app = function() {

    this.commands['/time'] = function(data) {
        bot.load(chat.user, function(data) {
            if(data==null) {
                timestamp = new Date().getTime();
                bot.save(chat.user,{
                    "timestamp": timestamp
                });
                print("timestamp saved for "+chat.user+"; ask me later to get elapsed time;");
                chat.send("this is first call. I'm saved your time");
            } else {
                timestamp = userdata.timestamp;
                timestamp = new Date().getTime() - timestamp;
                timestamp = timestamp/1000;
                print(timestamp+"seconds passed since "+chat.msg+" last ask");
                chat.send(timestamp+" seconds passed since your last ask");
                timestamp = new Date().getTime();
                bot.save(username,{
                    "timestamp": timestamp
                });
            }
        });
    };

    this.commands['/message'] = function(data) {
        print("some undefined command... sending it back");
        data.send("you send to me this: "+data.msg);
    };

    this.commands['/cities'] = function(data) {

    };

};

bot.onMessage(function(chat) {
    print("msg received! '"+chat.msg+"' from "+chat.user);
    var func = app.commands[chat.msg];
    print("and result func is: "+func);
    if(func==null) {
        app.commands['message'](chat);
        return;
    }
    func(chat);
});

