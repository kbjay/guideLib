package com.example.kjguidelib;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * * 自定义的引导页面
 *
 * @author kb_jay
 *         created at 2018/4/18 下午3:46
 */
public class KJGuideView<T> extends RelativeLayout {

    private int mGuideJumpBtBg;
    private int mGuideSkipMaginTop;
    private int mGuideSkipMarginRight;
    private int mGuideSkipTvSize;
    private int mGuideSkipTvColor;
    private int mGuideJumpTvColor;
    private int mGuideJumpTvSize;
    private int mGuideJumpWidth;
    private int mGuideJumpHeight;
    private int mGuidePointsSpan;
    private int mGuideJumpBottomSpan;
    private int mGuidePointBottomSpan;
    private int mGuidePointBg;
    private TextView mTvSkip;
    private String mGuideSkipText;
    private String mGuideJumpText;
    private RelativeLayout mRlJump;
    private LayoutParams lpPointsRoot;
    private LinearLayout mLlPointsRoot;
    private ViewPager mVp;
    private int mPageCount;
    private int mCurrentPos;
    private GuideEventListener mGuideEventListener;

    public KJGuideView(Context context) {
        this(context, null);
    }

    public KJGuideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KJGuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefaultAttrs(context);
        initAttrs(context, attrs);
        initViews(context);
    }

    private void initDefaultAttrs(Context context) {
        mGuideSkipMaginTop = KJSizeUtils.dp2px(context, 20);
        mGuideSkipMarginRight = KJSizeUtils.dp2px(context, 10);
        mGuideSkipTvSize = KJSizeUtils.sp2px(context, 16);
        mGuideSkipTvColor = Color.BLACK;
        mGuideSkipText = context.getString(R.string.skip);
        mGuideJumpTvColor = Color.BLACK;
        mGuideJumpTvSize = KJSizeUtils.sp2px(context, 16);
        mGuideJumpWidth = KJSizeUtils.dp2px(context, 100);
        mGuideJumpHeight = KJSizeUtils.dp2px(context, 40);
        mGuideJumpBottomSpan = KJSizeUtils.dp2px(context, 30);
        mGuideJumpText = context.getString(R.string.jump2home);
        mGuideJumpBtBg = R.drawable.bg_guide_bt;
        mGuidePointsSpan = KJSizeUtils.dp2px(context, 8);
        mGuidePointBottomSpan = KJSizeUtils.dp2px(context, 90);
        mGuidePointBg = R.drawable.bg_guide_point;
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.KJGuideView);
        int N = attributes.getIndexCount();
        for (int i = 0; i < N; i++) {
            initCustomAttr(attributes.getIndex(i), attributes);
        }
        attributes.recycle();
    }

    /**
     * * 获取自定义的属性
     *
     * @author kb_jay
     * created at 2018/4/18 下午4:13
     */

    private void initCustomAttr(int attr, TypedArray attributes) {
        if (attr == R.styleable.KJGuideView_guide_jump_bt_bg) {
            mGuideJumpBtBg = attributes.getResourceId(attr, mGuideJumpBtBg);

        } else if (attr == R.styleable.KJGuideView_guide_jump_bt_height) {
            mGuideJumpHeight = attributes.getDimensionPixelSize(attr, mGuideJumpHeight);

        } else if (attr == R.styleable.KJGuideView_guide_jump_bt_text_color) {
            mGuideJumpTvColor = attributes.getColor(attr, mGuideJumpTvColor);

        } else if (attr == R.styleable.KJGuideView_guide_jump_bt_text_size) {
            mGuideJumpTvSize = attributes.getDimensionPixelSize(attr, mGuideJumpTvSize);

        } else if (attr == R.styleable.KJGuideView_guide_jump_bt_width) {
            mGuideJumpWidth = attributes.getDimensionPixelOffset(attr, mGuideJumpWidth);

        } else if (attr == R.styleable.KJGuideView_guide_jump_bt_bottom_span) {
            mGuideJumpBottomSpan = attributes.getDimensionPixelOffset(attr, mGuideJumpBottomSpan);

        } else if (attr == R.styleable.KJGuideView_guide_jump_bt_text) {
            mGuideJumpText = attributes.getString(attr);

        } else if (attr == R.styleable.KJGuideView_guide_point_bottom_span) {
            mGuidePointBottomSpan = attributes.getDimensionPixelOffset(attr, mGuidePointBottomSpan);

        } else if (attr == R.styleable.KJGuideView_guide_point_inter_span) {
            mGuidePointsSpan = attributes.getDimensionPixelOffset(attr, mGuidePointsSpan);

        } else if (attr == R.styleable.KJGuideView_guide_point_bg) {
            mGuidePointBg = attributes.getResourceId(attr, R.drawable.bg_guide_point);

        } else if (attr == R.styleable.KJGuideView_guide_skip_margin_right) {
            mGuideSkipMarginRight = attributes.getDimensionPixelOffset(attr, mGuideSkipMarginRight);

        } else if (attr == R.styleable.KJGuideView_guide_skip_margin_top) {
            mGuideSkipMaginTop = attributes.getDimensionPixelOffset(attr, mGuideSkipMaginTop);

        } else if (attr == R.styleable.KJGuideView_guide_skip_text_color) {
            mGuideSkipTvColor = attributes.getColor(attr, mGuideSkipTvColor);

        } else if (attr == R.styleable.KJGuideView_guide_skip_text_size) {
            mGuideSkipTvSize = attributes.getDimensionPixelSize(attr, mGuideSkipTvSize);

        } else if (attr == R.styleable.KJGuideView_guide_skip_text) {
            mGuideSkipText = attributes.getString(attr);

        } else {
        }
    }

    /**
     * * 添加skip跟point跟jumpBt
     *
     * @param context
     * @author kb_jay
     * created at 2018/4/18 下午4:10
     */
    private void initViews(Context context) {

        mTvSkip = new TextView(context);
        mTvSkip.setText(mGuideSkipText);
        mTvSkip.setTextSize(TypedValue.COMPLEX_UNIT_PX, mGuideSkipTvSize);
        mTvSkip.setTextColor(mGuideSkipTvColor);

        LayoutParams lpSkip = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lpSkip.addRule(ALIGN_PARENT_RIGHT);
        lpSkip.setMargins(0, mGuideSkipMaginTop, mGuideSkipMarginRight, 0);

        addView(mTvSkip, lpSkip);

        LayoutParams lpJump = new LayoutParams(mGuideJumpWidth, mGuideJumpHeight);
        lpJump.addRule(ALIGN_PARENT_BOTTOM);
        lpJump.addRule(CENTER_HORIZONTAL);
        lpJump.setMargins(0, 0, 0, mGuideJumpBottomSpan);
        mRlJump = new RelativeLayout(context);
        mRlJump.setId(R.id.rl_jump);
        mRlJump.setBackgroundResource(mGuideJumpBtBg);

        addView(mRlJump, lpJump);

        LayoutParams lpJumpTv = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpJumpTv.addRule(CENTER_IN_PARENT);

        TextView mTvJump = new TextView(context);
        mTvJump.setTextColor(mGuideJumpTvColor);
        mTvJump.setTextSize(TypedValue.COMPLEX_UNIT_PX, mGuideJumpTvSize);
        mTvJump.setText(mGuideJumpText);

        mRlJump.addView(mTvJump, lpJumpTv);


        LayoutParams lpPointsRoot = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lpPointsRoot.addRule(CENTER_HORIZONTAL);
        lpPointsRoot.addRule(ALIGN_PARENT_BOTTOM);
        lpPointsRoot.setMargins(0, 0, 0, mGuidePointBottomSpan);
        mLlPointsRoot = new LinearLayout(context);
        mLlPointsRoot.setOrientation(LinearLayout.HORIZONTAL);
        addView(mLlPointsRoot, lpPointsRoot);

        mRlJump.setOnClickListener(new KJAvoidDoubleClickListener() {
            @Override
            public void onAvoidDoubleClick() {
                if (mGuideEventListener != null) {
                    mGuideEventListener.onClickJump();
                }
            }
        });
        mTvSkip.setOnClickListener(new KJAvoidDoubleClickListener() {
            @Override
            public void onAvoidDoubleClick() {
                if (mGuideEventListener != null) {
                    mGuideEventListener.onClickSkip();
                }
            }
        });
    }

    /**
     * *
     * 填充vp，暴漏给使用者，需要使用者用data填充vp的每一个itemView
     *
     * @author kb_jay
     * created at 2018/4/18 下午7:19
     */
    public void setData(int pageNumber, KJPageAdater.KJBindDataListener listener) {
        mPageCount = pageNumber;
        mCurrentPos = 0;
        mVp = new ViewPager(getContext());
        mVp.setAdapter(new KJPageAdater(mPageCount, listener));
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPos = position;
                onSelectedPointChange();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        LayoutParams lpVp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mVp, 0, lpVp);
        addPoints(getContext(), mPageCount);

        onSelectedPointChange();
    }

    /**
     * * 点击跳过跟跳转的回调，暴漏给使用者
     *
     * @author kb_jay
     * created at 2018/4/19 下午1:20
     */
    public void setGuideEventListener(GuideEventListener listener) {
        mGuideEventListener = listener;
    }

    private void addPoints(Context context, int number) {
        for (int i = 0; i < number; i++) {
            LinearLayout.LayoutParams lpPoint = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpPoint.setMargins(mGuidePointsSpan / 2, 0, mGuidePointsSpan / 2, 0);
            ImageView ivPoint = new ImageView(context);
            ivPoint.setImageResource(mGuidePointBg);
            mLlPointsRoot.addView(ivPoint, lpPoint);
        }
    }

    private void onSelectedPointChange() {
        for (int i = 0; i < mPageCount; i++) {
            if (i == mCurrentPos) {
                mLlPointsRoot.getChildAt(i).setSelected(true);
            } else {
                mLlPointsRoot.getChildAt(i).setSelected(false);
            }
        }
        mRlJump.setVisibility(mCurrentPos == mPageCount - 1 ? View.VISIBLE : View.GONE);
    }

    public interface GuideEventListener {
        void onClickSkip();

        void onClickJump();
    }
}
