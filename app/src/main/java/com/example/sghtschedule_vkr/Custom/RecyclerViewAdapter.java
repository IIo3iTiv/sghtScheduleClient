package com.example.sghtschedule_vkr.Custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sghtschedule_vkr.POJO.DatumPair;
import com.example.sghtschedule_vkr.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private final LayoutInflater inflater;
    private final List<DatumPair> dataModelArrayList;
    private final String user;
    View view;
    Date date;

    @SuppressLint("SimpleDateFormat")
    DateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd");
    @SuppressLint("SimpleDateFormat")
    DateFormat formatOut = new SimpleDateFormat("dd MMM");

    public RecyclerViewAdapter(Context ctx, List<DatumPair> dataModelArrayList, String user){
        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
        this.user = user;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerViewHolder holder, int position) {

        String subGroup = null;
        String teacher = null;

        if (user.equals("student")) {
            switch (dataModelArrayList.get(position).getSubgroup()) {
                case "1":
                    subGroup = view.getResources().getString(R.string.studentSubGroup_1);
                    break;
                case "2":
                    subGroup = view.getResources().getString(R.string.studentSubGroup_2);
                    break;
                case "3":
                    subGroup = view.getResources().getString(R.string.studentSubGroup_3);
                    break;
                case "4":
                    subGroup = view.getResources().getString(R.string.studentSubGroup_4);
                    break;
                case "5":
                    subGroup = view.getResources().getString(R.string.studentSubGroup_5);
                    break;
            }
            teacher = dataModelArrayList.get(position).getTeacher();
        }

        if (user.equals("teacher")) {
            switch (dataModelArrayList.get(position).getSubgroup()) {
                case "1":
                    subGroup = view.getResources().getString(R.string.teacherSubGroup_1);
                    break;
                case "2":
                    subGroup = view.getResources().getString(R.string.teacherSubGroup_2);
                    break;
                case "3":
                    subGroup = view.getResources().getString(R.string.teacherSubGroup_3);
                    break;
                case "4":
                    subGroup = view.getResources().getString(R.string.teacherSubGroup_4);
                    break;
                case "5":
                    subGroup = view.getResources().getString(R.string.studentSubGroup_5);
                    break;
            }
            teacher = dataModelArrayList.get(position).getGroup() + "-" + dataModelArrayList.get(position).getCoursStudy();
        }

        switch (dataModelArrayList.get(position).getSubgroup()) {
            case "1":
            case "2":
                holder.stick.setBackgroundColor(view.getResources().getColor(R.color.laboratory));
                holder.txtSubGroup.setTextColor(view.getResources().getColor(R.color.laboratory));
                holder.txtNumPair.setTextColor(view.getResources().getColor(R.color.laboratory));
                break;
            case "3":
                holder.stick.setBackgroundColor(view.getResources().getColor(R.color.general));
                holder.txtSubGroup.setTextColor(view.getResources().getColor(R.color.general));
                holder.txtNumPair.setTextColor(view.getResources().getColor(R.color.general));
                break;
            case "4":
            case "5":
                holder.stick.setBackgroundColor(view.getResources().getColor(R.color.foreign));
                holder.txtSubGroup.setTextColor(view.getResources().getColor(R.color.foreign));
                holder.txtNumPair.setTextColor(view.getResources().getColor(R.color.foreign));
                break;
        }

        if (position == dataModelArrayList.size() - 1) {
            holder.txtTimeBreak.setVisibility(View.INVISIBLE);
            holder.point.setVisibility(View.INVISIBLE);
        } else {
            holder.txtTimeBreak.setText("Перерыв, " + dataModelArrayList.get(position).getTimeBreak() + " минут");
        }

        try {
            date = formatIn.parse(dataModelArrayList.get(position).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (position == 0) {
            holder.txtDate.setText(formatOut.format(date));
        } else {
            holder.txtDate.setText("");
        }

        holder.txtNumPair.setText("#" + dataModelArrayList.get(position).getNumPair());
        holder.txtTime.setText(dataModelArrayList.get(position).getTimeStart() + " - " + dataModelArrayList.get(position).getTimeEnd());
        holder.txtDisc.setText(dataModelArrayList.get(position).getDiscipline());
        holder.txtAudit.setText(dataModelArrayList.get(position).getAuditorium());
        holder.txtTeacher.setText(teacher);
        holder.txtSubGroup.setText(subGroup);
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView txtNumPair, txtTime, txtDisc, txtAudit, txtTeacher, txtSubGroup, txtTimeBreak, txtDate;
        Button stick, point;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNumPair = itemView.findViewById(R.id.txtNumPair);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtTimeBreak = itemView.findViewById(R.id.txtTimeBreak);
            txtDisc = itemView.findViewById(R.id.txtDiscipline);
            txtAudit = itemView.findViewById(R.id.txtAudit);
            txtTeacher = itemView.findViewById(R.id.txtTeacher);
            txtSubGroup = itemView.findViewById(R.id.txtSubGroup);
            stick = itemView.findViewById(R.id.stick);
            point = itemView.findViewById(R.id.point);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }
}
