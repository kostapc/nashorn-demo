/* Please, forgive me... */

/* read and update player achievement context */
function initPlayer(/*ScriptDataContext*/ context) {
    println("player init done ("+context.getValue("ach_script")+")");
    context.subscribeToEvent("player_login");
}

function on_player_login(context) {
    // заход в игру X дней подряд
    // разделение по фактическим суткам
    var player = context.getController();
    var daysPassed = daysPassedFromLast(context);
    if(daysPassed<1 || daysPassed>=2) {
        return;
    }
    var hitCount = incrementCounter(context, "hit_counter");
    // 3, 5, 8, 12, 20
    if(hitCount==12) {
        var achId = context.getValue("ach_id");
        player.getAchievement().addAchievement(achId);
        println("achievement #"+achId+" given");
        context.unSubscribeFromEvent("player_login");
    }
}


/* global function in static content */
function install() {
    println("true_warrior_4.js script install called");
}

function on_debug_positive(context) {
    context.saveValue("hit_counter", 11);
    var d = new Date();
    d.setDate(d.getDate()-1);
    context.saveValue("last_date", d.getTime()-100000);
    on_player_login(context);
}