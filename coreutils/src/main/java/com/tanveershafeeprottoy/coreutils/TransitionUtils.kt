package com.tanveershafeeprottoy.coreutils

import android.transition.ChangeBounds
import android.transition.Explode
import android.transition.Fade
import android.transition.Transition
import android.view.animation.DecelerateInterpolator

object TransitionUtils {
    private var changeBounds: ChangeBounds? = null

    fun changeBounds(duration: Long): Transition {
        changeBounds = ChangeBounds()
        changeBounds?.duration = duration
        return changeBounds!!
    }

    fun changeBoundsDecelerate(duration: Long): Transition {
        changeBounds = ChangeBounds()
        changeBounds?.interpolator = DecelerateInterpolator()
        changeBounds?.duration = duration
        return changeBounds!!
    }

    fun fade(duration: Long): Transition {
        val fade = Fade()
        fade.duration = duration
        return fade
    }

    fun explode(duration: Long): Transition {
        val explode = Explode()
        explode.duration = duration
        return explode
    }
}