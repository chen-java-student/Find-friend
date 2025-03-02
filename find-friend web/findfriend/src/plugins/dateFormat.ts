/**
 * 格式化日期
 * @param time
 */
const dateFormat = (time) => {
    const date = new Date(time);
    //获取年
    const year = date.getFullYear();
    //获取月 小于10前面加0
    date.setMonth(date.getMonth() + 1);
    let month = date.getMonth() < 10? '0' + date.getMonth() : date.getMonth();
    //如果日期是0，就是12月
    if (date.getMonth() === 0){
        month = 12;
    }
    //获取日
    const day = date.getDate() < 10? '0' + date.getDate() : date.getDate();
    // const day = date.getDay().toString().length < 10? '0' + date.getDay().toString() : date.getDay().toString();
    // console.log("y= " + year + ",m= "+ month + "，d= " + day)
    //获取小时和分钟
    const hours = date.getHours() < 10? '0' + date.getHours() : date.getHours();
    const minutes = date.getMinutes() < 10? '0' + date.getMinutes() : date.getMinutes();
    return `${year}-${month}-${day} ${hours}:${minutes}`
};

export default dateFormat;