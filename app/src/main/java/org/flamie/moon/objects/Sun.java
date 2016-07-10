package org.flamie.moon.objects;

//import static org.flamie.moon.util.Dimen.dp;

public class Sun {

    private float xSun;
    private float ySun;
    private float targetPosition = 1300f;
    private float originalPosition = 4f;
    private float distance = targetPosition - originalPosition;

    // Night sky
    private int skyRedTop = 90;
    private int skyGreenTop = 100;
    private int skyBlueTop = 190;
    private int skyRedBottom = 40;
    private int skyGreenBottom = 50;
    private int skyBlueBottom = 140;

    public void sunMoving() {
        float sunSpeed = 200f;

        if(xSun < 2500f) {
            xSun += distance / sunSpeed;
        } else {
            xSun =- distance / sunSpeed;
            skyRedTop = 90;
            skyGreenTop = 100;
            skyBlueTop = 190;
            skyRedBottom = 40;
            skyGreenBottom = 50;
            skyBlueBottom = 140;
        }
        ySun = (float) (500 * Math.sin(Math.PI * (Math.abs(xSun + originalPosition)) / distance));
    }

    public void sunrise() {
        if(ySun > 150 && ySun < 220) {
            skyRedBottom += 10;
            skyGreenBottom += 5;
        } else if(ySun < 150 && skyRedBottom > 40 && skyGreenBottom > 40) {
            skyRedBottom += -10;
            skyGreenBottom += -5;
        }
    }

    public void colorBottom() {
        if(skyBlueBottom < 240 && ySun > 200 && skyGreenBottom < 180) {
            // day
            skyBlueBottom += 10;
            skyGreenBottom += 10;
        } else if(skyBlueBottom > 140 && ySun < 150 && skyGreenBottom > 50 && xSun > 2000f) {
            // night
            skyBlueBottom += -10;
            skyGreenBottom += -10;
        }
    }

    public void colorTop() {
        if(skyBlueTop <= 240 && ySun > 200 && skyGreenTop <= 200 && skyRedTop <= 110) {
            // day
            skyBlueTop += 10;
            skyGreenTop += 10;
            skyRedTop += 5;
        } else if(skyBlueTop >= 190 && ySun <= 150 && skyRedTop >= 90 && skyGreenTop >= 100) {
            // night
            skyBlueTop += -10;
            skyGreenTop += -10;
            skyRedTop += -5;
        }
    }

    public float getxSun() {
        return xSun;
    }

    public float getySun() {
        return ySun;
    }

    public int getSkyRedTop() {
        return skyRedTop;
    }

    public int getSkyGreenTop() {
        return skyGreenTop;
    }

    public int getSkyBlueTop() {
        return skyBlueTop;
    }

    public int getSkyRedBottom() {
        return skyRedBottom;
    }

    public int getSkyGreenBottom() {
        return skyGreenBottom;
    }

    public int getSkyBlueBottom() {
        return skyBlueBottom;
    }
}
