package com.example.administrator.kuaidi.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.kuaidi.ModelBean.ChatMessage;
import com.example.administrator.kuaidi.R;

import java.util.List;

import me.himanshusoni.chatmessageview.ChatMessageView;

/**
 * Created by himanshusoni on 06/09/15.
 */
public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.MessageHolder> {
    private final String TAG = "ChatMessageAdapter";
    private static final int MY_MESSAGE = 0, OTHER_MESSAGE = 1;

    private List<ChatMessage> mMessages;
    private Context mContext;

    public ChatMessageAdapter(Context context, List<ChatMessage> data) {
        mContext = context;
        mMessages = data;
    }

    @Override
    public int getItemCount() {
        return mMessages == null ? 0 : mMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.e(TAG, "getItemViewType: " +  position);
        ChatMessage item = mMessages.get(position);

        if (item.isMine()) return MY_MESSAGE;
        else return OTHER_MESSAGE;
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == MY_MESSAGE) {
            return new MessageHolder(LayoutInflater.from(mContext).inflate(R.layout.item_mine_message, parent, false));
        } else {
            return new MessageHolder(LayoutInflater.from(mContext).inflate(R.layout.item_other_message, parent, false));
        }
    }
    public void clear(){
        mMessages.clear();
    }
    public void add(ChatMessage message) {
        mMessages.add(message);
        notifyItemInserted(mMessages.size() - 1);
    }

    @Override
    public void onBindViewHolder(final MessageHolder holder, final int position) {
        ChatMessage chatMessage = mMessages.get(position);
        if (chatMessage.isImage()) {
            holder.ivImage.setVisibility(View.VISIBLE);
            holder.tvMessage.setVisibility(View.GONE);
            holder.tvUser.setText(chatMessage.getUsername());
            holder.ivImage.setImageResource(R.mipmap.me);
        } else {
            holder.ivImage.setVisibility(View.GONE);
            holder.tvMessage.setVisibility(View.VISIBLE);
            holder.tvUser.setText(chatMessage.getUsername());
            holder.tvMessage.setText(chatMessage.getContent());
        }

        //String date = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(new Date());
        holder.tvTime.setText(chatMessage.getCreateTime());

        holder.chatMessageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ChatMessage item = mMessages.get(position);
                if(!item.isMine()){
                    if(mItemClickListener != null){
                        mItemClickListener.onItemClick(position);
                    }
                }

               // Intent intent = new Intent(mContext,ChatMsgActivity.class);
               // intent.putExtra("uid",mMessages.get(position).getUsername());
               // mContext.startActivity(intent);
            }
        });
    }

    class MessageHolder extends RecyclerView.ViewHolder {
        TextView tvUser;
        TextView tvMessage, tvTime;
        ImageView ivImage;
        ChatMessageView chatMessageView;

        MessageHolder(View itemView) {
            super(itemView);
            chatMessageView = (ChatMessageView) itemView.findViewById(R.id.chatMessageView);
            tvMessage = (TextView) itemView.findViewById(R.id.tv_message);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            tvUser = (TextView)itemView.findViewById(R.id.username);
        }
    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener onItemClickListener){
        mItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(int postiton);
    }
}