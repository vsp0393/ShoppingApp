import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.vsple.purchaseapp.R
import java.util.Objects
class ViewPagerAdapter(private val mContext: Context, private val itemList: List<Int>) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(mContext)

        val view = layoutInflater!!.inflate(R.layout.item_view_pager, container, false)
        var imageView: ImageView = view.findViewById(R.id.imageViewMain)
        imageView.setImageResource(itemList[position])
        container.addView(view, position)

        return view
    }


    override fun getCount(): Int {
        return itemList.size
    }


    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}