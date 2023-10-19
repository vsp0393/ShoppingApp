package com.vsple.purchaseapp.Activity

import ViewPagerAdapter
import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vsple.purchaseapp.Adapters.ColorSelectionAdapter
import com.vsple.purchaseapp.Adapters.ItemSizeAdapter
import com.vsple.purchaseapp.Models.ItemDetailModel
import com.vsple.purchaseapp.Models.Size
import com.vsple.purchaseapp.R
import com.vsple.purchaseapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemDetail: ItemDetailModel // Declare the variable
    private var finalItemPrice: Int = 0
    private var dialog: Dialog? = null
    var mViewPagerAdapter: ViewPagerAdapter? = null
    var itemSizeAdapter: ItemSizeAdapter? = null
    var colorSelectionAdapter: ColorSelectionAdapter? = null
    var selectedSize: String = "Medium"
    var defaultItemSelected: Int = 1
    var defaultSelectedColor: String = "White"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //Function call to update ui with data
        addItemDetails()

        binding.edtSize.setOnClickListener {
            showSizeSelectionDialog(itemDetail.size)
        }

        binding.rlSizeOne.setOnClickListener {
            showSizeSelectionDialog(itemDetail.size)
        }

        binding.edtQuantity.setOnClickListener {
            showAddItemSelection()
        }
        binding.rlQuantityOne.setOnClickListener {
            showAddItemSelection()
        }

        binding.edtColor.setOnClickListener {
            showColorSelectionDialog(itemDetail.colorDetails)
        }
        binding.rlColorOne.setOnClickListener {
            showColorSelectionDialog(itemDetail.colorDetails)
        }
    }

    //Put data to Model
    private fun addItemDetails() {
        itemDetail = ItemDetailModel(
            cartItemCount = 2,
            discount = 50,
            itemDescription = "A classic cotton T-shirt tagged with our logo in contrast lettering...",
            storeName = "Blueberry store",
            imageList = listOf(
                R.drawable.item_image,
                R.drawable.imagetwo,
                R.drawable.imagethree,
                R.drawable.imagefour
            ),
            price = 5000,
            productName = "Logo Print Cotton T-shirt",
            size = listOf(
                Size(
                    availability = true,
                    prize = 1299,
                    sizeType = "Small",
                    selectedSize = false
                ),
                Size(
                    availability = true,
                    prize = 1299,
                    sizeType = "Medium",
                    selectedSize = false
                ),
                Size(
                    availability = false,
                    prize = 1299,
                    sizeType = "Large",
                    selectedSize = false
                ),
                Size(
                    availability = true,
                    prize = 1299,
                    sizeType = "Xtra Large",
                    selectedSize = false

                ),
                Size(
                    availability = true,
                    prize = 1299,
                    sizeType = "XXtra Large",
                    selectedSize = false
                )
            ),
            colorDetails = listOf("Black", "White", "Red", "Green", "Blue"),
            watchedUser = 13
        )
        setDataToUi()
    }


    //Function to set data on Ui
    @SuppressLint("SetTextI18n")
    private fun setDataToUi() {

        binding.tvProductName.text = itemDetail.productName ?: ""
        binding.tvShortDescription.text = itemDetail.itemDescription ?: ""
        binding.toolbar.tvCartCount.text = itemDetail.cartItemCount.toString() ?: "0"
        binding.edtSize.setText(selectedSize)
        binding.edtColor.setText(defaultSelectedColor)

        //Calculate price after discount
        val itemPrice = itemDetail.price ?: 0
        val discount = itemDetail.discount ?: 0
        val countWatched = itemDetail.watchedUser ?: 0

        finalItemPrice = itemPrice - (itemPrice * discount / 100)

        //set price after discount
        binding.tvPrice.text = "₹ " + finalItemPrice.toString()
        binding.tvDiscount.text = "₹ $itemPrice"
        binding.tvDiscountPercent.text = "($discount% off)"
        binding.tvDiscount.paintFlags =
            binding.tvDiscount.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        binding.tvTotalWatched.text = "$countWatched people bought this in the last 24 hours."
        binding.tvStoreName.text = itemDetail.storeName
        binding.edtQuantity.setText("Total item $defaultItemSelected")

        binding.segmentProgress.segmentCount = itemDetail.imageList.size
        binding.viewPagerMain.offscreenPageLimit = itemDetail.imageList.size
        mViewPagerAdapter = ViewPagerAdapter(this, itemDetail.imageList)
        binding.viewPagerMain.adapter = mViewPagerAdapter
        binding.segmentProgress.viewPager = binding.viewPagerMain
        binding.segmentProgress.start()

        //Set data to image slider
        mViewPagerAdapter =
            ViewPagerAdapter(this@MainActivity, itemDetail.imageList)
        // Adding the Adapter to the ViewPager
        binding.viewPagerMain.adapter = mViewPagerAdapter

    }

    //Dialog to select size
    @SuppressLint("MissingInflatedId")
    private fun showSizeSelectionDialog(sizeList: List<Size>) {
        try {
            val mBottomSheetDialog = BottomSheetDialog(this@MainActivity)
            val view = layoutInflater.inflate(R.layout.item_size_selection_bottom_dialog, null)
            val recyclerView = view.findViewById<RecyclerView>(R.id.rvSize)
            val tvDone = view.findViewById<TextView>(R.id.appCompatSet)
            dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
            dialog?.setOnShowListener {
                BottomSheetBehavior.from(view!!).state = BottomSheetBehavior.STATE_EXPANDED
            }

            val layoutManager = LinearLayoutManager(this)
            recyclerView.layoutManager = layoutManager
            itemSizeAdapter = ItemSizeAdapter(this, sizeList)
            recyclerView.adapter = itemSizeAdapter
            itemSizeAdapter?.setOnClickListener(object :
                ItemSizeAdapter.OnClickListener {
                override fun onButtonClick(position: Int) {
                    selectedSize = itemDetail.size.get(position).sizeType
                }
            })
            tvDone.setOnClickListener {
                binding.rlSizeOne.visibility = View.GONE
                binding.edtName.visibility = View.VISIBLE
                binding.edtSize.setText(selectedSize)
                mBottomSheetDialog.dismiss()
            }
            mBottomSheetDialog.setContentView(view)

            val mBehavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(view.parent as View)
            mBehavior.peekHeight = 2500
            mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            mBottomSheetDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //Dialog to select color
    @SuppressLint("MissingInflatedId")
    private fun showColorSelectionDialog(colorList: List<String>) {
        try {
            val mBottomSheetDialog = BottomSheetDialog(this@MainActivity)
            val view = layoutInflater.inflate(R.layout.item_size_selection_bottom_dialog, null)
            val recyclerView = view.findViewById<RecyclerView>(R.id.rvSize)
            val tvDone = view.findViewById<TextView>(R.id.appCompatSet)
            dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
            dialog?.setOnShowListener {
                BottomSheetBehavior.from(view!!).state = BottomSheetBehavior.STATE_EXPANDED
            }

            val layoutManager = LinearLayoutManager(this)
            recyclerView.layoutManager = layoutManager
            colorSelectionAdapter = ColorSelectionAdapter(this, colorList)
            recyclerView.adapter = colorSelectionAdapter
            colorSelectionAdapter?.setOnClickListener(object :
                ColorSelectionAdapter.OnClickListener {
                override fun onButtonClick(position: Int) {
                    defaultSelectedColor = itemDetail.colorDetails.get(position)
                }
            })
            tvDone.setOnClickListener {
                binding.edtcl.visibility = View.VISIBLE
                binding.rlColorOne.visibility = View.GONE
                binding.edtColor.setText(defaultSelectedColor)
                mBottomSheetDialog.dismiss()
            }
            mBottomSheetDialog.setContentView(view)

            val mBehavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(view.parent as View)
            mBehavior.peekHeight = 2500
            mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            mBottomSheetDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //Dialog to add Item
    @SuppressLint("MissingInflatedId")
    private fun showAddItemSelection() {
        try {
            val mBottomSheetDialog = BottomSheetDialog(this@MainActivity)
            val view = layoutInflater.inflate(R.layout.item_add_item, null)
            val tvItemCount = view.findViewById<TextView>(R.id.tvItemCount)
            val imageAddItem = view.findViewById<ImageView>(R.id.imageAddItem)
            val imageRemoveItem = view.findViewById<ImageView>(R.id.imageRemoveItem)
            val tvDone = view.findViewById<TextView>(R.id.appCompatSet)
            tvItemCount.text = defaultItemSelected.toString()
            dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
            dialog?.setOnShowListener {
                BottomSheetBehavior.from(view!!).state = BottomSheetBehavior.STATE_EXPANDED
            }
            imageAddItem.setOnClickListener {
                if (defaultItemSelected <= 10)
                    defaultItemSelected++
                tvItemCount.text = defaultItemSelected.toString()
                binding.edtQuantity.setText("Total item $defaultItemSelected")
            }
            imageRemoveItem.setOnClickListener {
                if (defaultItemSelected > 1) {
                    defaultItemSelected--
                    tvItemCount.text = defaultItemSelected.toString()
                    binding.edtQuantity.setText("Total item $defaultItemSelected")
                }
            }
            tvDone.setOnClickListener {
                binding.rlQuantityOne.visibility = View.GONE
                binding.edtQnt.visibility = View.VISIBLE
                binding.edtQuantity.setText("Total item $defaultItemSelected")
                mBottomSheetDialog.dismiss()
            }
            mBottomSheetDialog.setContentView(view)

            val mBehavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(view.parent as View)
            mBehavior.peekHeight = 2500
            mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            mBottomSheetDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}