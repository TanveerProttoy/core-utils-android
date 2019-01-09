package com.tanveershafeeprottoy.coreutils;

import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Transition;
import android.view.animation.DecelerateInterpolator;

/**
 * @author Tanveer Shafee Prottoy
 */

public class TransitionUtils {
    private static ChangeBounds changeBounds;

    public static Transition changeBounds(long duration) {
        changeBounds = new ChangeBounds();
        changeBounds.setDuration(duration);
        return changeBounds;
    }

    public static Transition changeBoundsDecelerate(long duration) {
        changeBounds = new ChangeBounds();
        changeBounds.setInterpolator(new DecelerateInterpolator());
        changeBounds.setDuration(duration);
        return changeBounds;
    }

    public static Transition fade(long duration) {
        Fade fade = new Fade();
        fade.setDuration(duration);
        return fade;
    }

    public static Transition explode(long duration) {
        Explode explode = new Explode();
        explode.setDuration(duration);
        return explode;
    }
}
