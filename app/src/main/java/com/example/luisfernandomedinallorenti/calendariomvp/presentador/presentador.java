package com.example.luisfernandomedinallorenti.calendariomvp.presentador;

public interface presentador<T> {
    void addView(T view);
    void removeView();
}
