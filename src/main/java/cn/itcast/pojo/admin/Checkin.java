package cn.itcast.pojo.admin;

import java.io.Serializable;
import java.util.Date;

/**
 * 入住管理的实体类
 */
public class Checkin implements Serializable {
    /*****************************私有属性*****************************************/
    private Long id;//入住id
    private Long roomId;//房间id
    private Long roomTypeId;//房型id
    private Float checkinPrice;//入住价格
    private String name;//入住者姓名
    private String idCard;//身份证号码
    private String mobile;//手机号
    private int status;//状态：0：入住中，1：已结算离店
    private String arriveDate;//入住日期
    private String leaveDate;//离店日期
    private Date createTime;//创建时间
    private Long bookOrderId;//预定订单id，可为空
    private String remark;
    /*********************************set/get**************************************/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Float getCheckinPrice() {
        return checkinPrice;
    }

    public void setCheckinPrice(Float checkinPrice) {
        this.checkinPrice = checkinPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getBookOrderId() {
        return bookOrderId;
    }

    public void setBookOrderId(Long bookOrderId) {
        this.bookOrderId = bookOrderId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
