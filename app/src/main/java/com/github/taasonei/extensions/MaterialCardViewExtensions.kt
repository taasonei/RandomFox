package com.github.taasonei.extensions

import android.view.MotionEvent
import android.view.View
import com.google.android.material.card.MaterialCardView

fun MaterialCardView.onTouch(touch: (view: View, motionEvent: MotionEvent) -> Unit) {
    setOnTouchListener { v, event ->
        touch(v, event)
        v.performClick()
        true
    }
}