package id.gobang.app.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import id.gobang.app.Model.Product;
import id.gobang.app.R;

public class ProductViewHolder extends ChildViewHolder {
    private TextView mTextView;

    public ProductViewHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.textView);
    }

    public void bind(Product product) {
        mTextView.setText(product.name);
    }
}
