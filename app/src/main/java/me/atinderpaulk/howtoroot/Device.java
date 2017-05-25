package me.atinderpaulk.howtoroot;


class Device {

    private String phone_name;
    private int imageUrl;

    Device(String phone_name, int imageUrl) {
        this.phone_name = phone_name;
        this.imageUrl = imageUrl;
    }

    String getTitle() {
        return phone_name;
    }

    int getImageUrl() {
        return imageUrl;
    }

}
