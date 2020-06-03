package com.example.bekang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private SensorsAdapter mSensorsAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Sensor> dummydata, List<String > keys){
        mContext = context;
        mSensorsAdapter = new SensorsAdapter(dummydata, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mSensorsAdapter);
    }

    class SensorItemView extends RecyclerView.ViewHolder {
        private TextView mData;
        private TextView mParameter;
        private TextView mNilai;
        private String key;

        public SensorItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.sensor_list_item, parent, false));
            mData = (TextView) itemView.findViewById(R.id.data_txtView);
            mNilai = (TextView) itemView.findViewById(R.id.nilai_txtView);
            mParameter = (TextView) itemView.findViewById(R.id.parameter_txtView);

        }
        public void bind (Sensor sensor, String key){
            mData.setText(sensor.getData());
            mParameter.setText(sensor.getParameter());
            mNilai.setText(sensor.getNilai());
            this.key = key;
        }

    }
    class SensorsAdapter extends RecyclerView.Adapter<SensorItemView>{
        private List<Sensor> mSensorList;
        private List<String> mKeys;

        public SensorsAdapter(List<Sensor> mSensorList, List<String> mKeys) {
            this.mSensorList = mSensorList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public SensorItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SensorItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SensorItemView holder, int position) {
            holder.bind(mSensorList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mSensorList.size();
        }
    }
}
