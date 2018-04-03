function testLibIncluding() {
    println("lib included to script");
}

function incrementCounter(context, counterName) {
    var count = context.getValue(counterName);
    if(!count || count==null) {
        count = 0;
    }
    count ++;
    context.saveValue(counterName, count);
    context.setCounter(count);
    return count;
}

function incrementCounterForNumber(context, counterName, addValue) {
    var count = parseFloat(context.getValue(counterName));
    if(count==null) {
        count = 0;
    }
    count = (addValue + count) | 0;
    context.saveValue(counterName, count);
    context.setCounter(count);
    return count;
}

function millsPassed(lastDate) {
    var date1 = new Date();
    var ms = Math.abs(date1.getTime()-lastDate);
    // println("ms " + ms);
    return ms;
}

function daysPassedFromLast(context) {
    var pastDate = context.getValue("last_date");
    if(pastDate==null) {
        pastDate = new Date().getTime();
        context.saveValue("last_date", pastDate);
        return 0;
    }
    return millsPassed(pastDate)/1000/60/60/24;
}