package edu.hanu.employeemanagementsystem.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.hanu.employeemanagementsystem.EmployeeListener;
import edu.hanu.employeemanagementsystem.R;
import edu.hanu.employeemanagementsystem.models.Employee;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder> implements Filterable {

    private Context context;
    private List<Employee> list;
    private List<Employee> listTemp;
    private EmployeeListener listener;

    public EmployeeAdapter(Context context, EmployeeListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setData(List<Employee> list) {
        this.list = list;
        this.listTemp= list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeAdapter.EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new EmployeeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {
        Employee employee = list.get(position);
        holder.bindClick(employee);
    }

    @Override
    public int getItemCount() {
        if(list != null) return list.size();
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()) {
                    list = listTemp;
                } else {
                    ArrayList<Employee> employees = new ArrayList<>();
                    for(Employee emp: listTemp) {
                        if(emp.getFullName().contains(strSearch)) {
                            employees.add(emp);
                        }
                    }

                    list = employees;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (ArrayList<Employee>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class EmployeeHolder extends RecyclerView.ViewHolder {
        private TextView tvName2, tvBirthDay2, tvPhone2, tvEmail2;
        private ImageButton btnEdit, btnDelete;

        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);

            initViewEmployee();
        }


        private void initViewEmployee() {
            tvName2 = itemView.findViewById(R.id.tvName2);
            tvBirthDay2 = itemView.findViewById(R.id.tvBirthDay2);
            tvPhone2 = itemView.findViewById(R.id.tvPhone2);
            tvEmail2 = itemView.findViewById(R.id.tvEmail2);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

        public void bindClick(Employee employee) {

            tvName2.setText(employee.getFullName());
            tvBirthDay2.setText("Birthday: " + employee.getBirthDay());
            tvPhone2.setText("Phone: " + employee.getPhone());
            tvEmail2.setText("Email: " + employee.getEmail());

            btnEdit.setOnClickListener(view -> {
                listener.updateEmployee(employee);
            });

            btnDelete.setOnClickListener(view -> {
                listener.deleteEmployee(employee);
            });
        }
    }
}
