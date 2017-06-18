package cn.focus.house.model;

public class Review {
    private String agentName;
    private String agentPhone;
    private String review;
    private String date;

    public Review() {
    }

    public Review(String agentName, String agentPhone, String review, String date) {
        this.agentName = agentName;
        this.agentPhone = agentPhone;
        this.review = review;
        this.date = date;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentPhone() {
        return agentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
