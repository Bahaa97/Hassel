package com.bahaa.haseel.screen.checkOut

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.DatePicker
import android.widget.TimePicker
import com.bahaa.haseel.R
import com.bahaa.haseel.application.MyApp.Companion.language
import com.bahaa.haseel.base.BaseActivity
import com.bahaa.haseel.data.models.*
import com.bahaa.haseel.databinding.ActivityCheckOutBinding
import com.bahaa.haseel.databinding.BottomSheetOnlinePayBinding
import com.bahaa.haseel.screen.splash.SplashActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_check_out.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.reflect.KClass

class CheckOutActivity : BaseActivity<ActivityCheckOutBinding, CheckOutViewModelImp>(),
    PaymentAdapter.PaymentAction, ProductAdapter.ProductAction, CardsAdapter.CardsAction,
    DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    override fun resourceId(): Int = R.layout.activity_check_out

    override fun viewModelClass(): KClass<CheckOutViewModelImp> = CheckOutViewModelImp::class


    private var day: Int = 0
    private var month: Int = 0
    private var year: Int = 0
    private var minute: Int = 0
    private var hour: Int = 0
    private lateinit var cardBottomSheet: BottomSheetDialog
    private lateinit var cardBottomSheetView: BottomSheetOnlinePayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        changeLang()
        super.onCreate(savedInstanceState)
        setUI()
        click()
    }

    private fun click() {
        btnChangeCard.setOnClickListener {
            cardBottomSheet.show()
        }
        btnShowItems.setOnClickListener {
            dataBinding.hidden = false
            recyclerProducts.adapter = ProductAdapter(getProducts(), this)
        }
        btnHideItems.setOnClickListener {
            dataBinding.hidden = true
            setProduct()
        }
        btnChangeLang.setOnClickListener {
            if (language == "ar") {
                sharedPreferences.edit().putString("lang", "en").apply()
                language = "en"
            } else {
                sharedPreferences.edit().putString("lang", "ar").apply()
                language = "ar"
            }
            startActivity(Intent(this, SplashActivity::class.java))
            overridePendingTransition(R.anim.slide_up, R.anim.no_change)
            finishAffinity()
        }
        btnSelectDate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val datePickerDialog =
                DatePickerDialog(
                    this,
                    this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
            datePickerDialog.show()
        }
    }

    private fun setUI() {
        dataBinding.hidden = true
        recyclerPayment.adapter = PaymentAdapter(getPaymentWay(), this)
        recyclerPayment.itemAnimator?.changeDuration = 0
        setProduct()
        setUpBottomSheet()
    }

    private fun setProduct() {
        val products: MutableList<ProductDataModel> = arrayListOf()
        products.add(getProducts()[0])
        products.add(getProducts()[1])
        products.add(getProducts()[2])
        recyclerProducts.adapter = ProductAdapter(products, this)
    }

    private fun setUpBottomSheet() {
        cardBottomSheetView = BottomSheetOnlinePayBinding.inflate(LayoutInflater.from(this))
        cardBottomSheetView.lifecycleOwner = this
        cardBottomSheetView.recyclerCards.adapter = CardsAdapter(getCards(), this)
        cardBottomSheet = BottomSheetDialog(this, R.style.BaseBottomSheetDialog)
        cardBottomSheet.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        cardBottomSheet.setContentView(cardBottomSheetView.root)
    }

    override fun onPaymentClick(item: PaymentWayDataModel) {
        dataBinding.readyToCheckOut = true
        if (item.type == "visa") {
            cardBottomSheet.show()
        } else {
            dataBinding.onlinePay = false
        }
    }

    override fun onProductClick(item: ProductDataModel) {

    }

    override fun onCardClick(item: CardDataModel) {
        cardBottomSheet.dismiss()
        dataBinding.card = item
        dataBinding.onlinePay = true
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        this.day = day
        this.year = year
        this.month = month
        val calendar: Calendar = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog(
            this, this, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE),
            DateFormat.is24HourFormat(this)
        )
        timePickerDialog.show()
    }

    override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
        txtDate.text =
            day.toString() + " / " + month.toString() + " - " +String.format("%02d:%02d", hourOfDay, minute)
        txtDate.setTextColor(resources.getColor(R.color.black))
    }

}