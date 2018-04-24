
// this is function and it;s not called by default. Need to ask nashorn to call it when it compiled
(function(_bot) {
    print("main function runned!");

    var BotApp = function($bot) {
        var that = this;
        this.sendCallback = function(res) {
            print("send message result is "+res);
        };
        this.saveCallback = function(res) {
            print("data save result is "+res);
        };
        this.commands = {
            "/time": function(chat) {
                         print("/time function called! "+$bot);
                         $bot.load(chat.user, function(userdata) {
                             print("data loaded: "+userdata);
                             if(userdata==null) {
                                 timestamp = new Date().getTime();
                                 $bot.save(chat.user,{
                                     "timestamp": timestamp
                                 }, that.saveCallback);
                                 print("timestamp saved for "+chat.user+"; ask me later to get elapsed time;");
                                 chat.send("this is first call. I'm saved your time",that.sendCallback);
                             } else {
                                 timestamp = userdata.timestamp;
                                 timestamp = new Date().getTime() - timestamp;
                                 timestamp = timestamp/1000;
                                 print(timestamp+"seconds passed since "+chat.msg+" last ask");
                                 chat.send(timestamp+" seconds passed since your last ask",function(res){
                                    print("send elapsed time for "+chat.user+" is "+res);
                                 });
                                 timestamp = new Date().getTime();
                                 $bot.save(chat.user,{
                                     "timestamp": timestamp
                                 }, that.saveCallback);
                             }
                         });
                     },
            "/message": function(chat) {
                         print("some undefined command... sending it back");
                         chat.send("you send to me this: ```"+chat.msg+"```, this is ditch...", function(res) {
                            print("send back result for "+chat.user+" is "+res);
                         });
                     }

        };

        this.getFunc = function(msg) {
            var cmd = this.commands[msg];
            //print("cmd is "+cmd);
            if(typeof cmd == "undefined") {
                return this.commands["/message"];
            }
            return cmd;
        }
    };

    var app = new BotApp(_bot);

    _bot.onMessage(function(chat) {
        print("msg received!!! '"+chat.msg+"' from "+chat.user);
        var func = app.getFunc(chat.msg);
        func(chat);
        print("onMessage done");
    });

})(bot);

