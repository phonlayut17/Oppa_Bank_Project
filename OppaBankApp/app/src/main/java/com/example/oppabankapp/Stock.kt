package com.example.oppabankapp

class Stock (var name: String, var score: Float) {
    companion object {
        fun getSampleStudentData(size: Int): ArrayList<Stock> {
            val stock: ArrayList<Stock> = ArrayList()
            for (i in 1 until size) {
                if (i == 1) {
                    stock.add(Stock("เงินฝากประจำ", Math.random().toFloat() * 100))
                } else if (i == 2) {
                    stock.add(Stock("ดอกเบี้ย", Math.random().toFloat() * 100))
                } else if (i == 3) {
                    stock.add(Stock("หุ้นส่วน", Math.random().toFloat() * 100))
                } else if (i == 4) {
                    stock.add(Stock("สปอนเซอร์", Math.random().toFloat() * 100))
                } else {
                    stock.add(Stock("อื่นๆ", Math.random().toFloat() * 100))
                }
            }
            return stock
        }
    }

}