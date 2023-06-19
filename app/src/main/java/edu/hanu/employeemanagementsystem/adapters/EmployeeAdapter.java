package edu.hanu.employeemanagementsystem.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.hanu.employeemanagementsystem.EmployeeListener;
import edu.hanu.employeemanagementsystem.R;
import edu.hanu.employeemanagementsystem.models.Employee;
import edu.hanu.employeemanagementsystem.models.Experience;
import edu.hanu.employeemanagementsystem.models.Fresher;
import edu.hanu.employeemanagementsystem.models.Intern;
import edu.hanu.employeemanagementsystem.views.MainActivity;

public class EmployeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private static final int EXPERIENCE = 0;
    private static final int FRESHER = 1;
    private static final int INTERN = 2;
    private Context context;
    private ArrayList<Employee> list;
    private ArrayList<Employee> listTemp;
    private EmployeeListener listener;

    public EmployeeAdapter(Context context, ArrayList<Employee> list, EmployeeListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
        this.listTemp = list;
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position) instanceof Experience) {
            return EXPERIENCE;
        } else if(list.get(position) instanceof Fresher) {
            return FRESHER;
        } else if(list.get(position) instanceof Intern) {
            return INTERN;
        }
       return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case EXPERIENCE:
                View view = inflater.inflate(R.layout.item_experience, parent, false);
                return new ExperienceHolder(view);

            case FRESHER:
                View view1 = inflater.inflate(R.layout.item_fresher, parent, false);
                return new FresherHolder(view1);

            case INTERN:
                View view2 = inflater.inflate(R.layout.item_intern, parent, false);
                return new InternHolder(view2);

        }
        return new ExperienceHolder(inflater.inflate(R.layout.item_experience, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case EXPERIENCE:
                if(list.get(position) instanceof Experience) {
                    Experience experience = (Experience) list.get(position);

                    ExperienceHolder experienceHolder = (ExperienceHolder) holder;
                    experienceHolder.tvName.setText(experience.getFullName());
                    experienceHolder.tvBirthDay.setText("Birthday: " + experience.getBirthDay());
                    experienceHolder.tvPhone.setText("Phone: " + experience.getPhone());
                    experienceHolder.tvEmail.setText("Email: " + experience.getEmail());
                    experienceHolder.tvExpInYear.setText("Experience in year: "+experience.getExpInYear());
                    experienceHolder.tvProSkill.setText("Professional skill: " + experience.getProSkill());
                    experienceHolder.bindClick(experience, position);
                }

                break;
            case FRESHER:
                if(list.get(position) instanceof Fresher) {
                    Fresher fresher = (Fresher) list.get(position);

                    FresherHolder fresherHolder = (FresherHolder) holder;
                    fresherHolder.tvName.setText(fresher.getFullName());
                    fresherHolder.tvBirthDay.setText("Birthday: " + fresher.getBirthDay());
                    fresherHolder.tvPhone.setText("Phone: " + fresher.getPhone());
                    fresherHolder.tvEmail.setText("Email: " + fresher.getEmail());
                    fresherHolder.tvGraduationDate.setText("Graduation date: " + fresher.getGraduationDate());
                    fresherHolder.tvGraduationRank.setText("Graduation rank: " + fresher.getGraduationRank());
                    fresherHolder.tvEducation.setText("Education: " + fresher.getEducation());
                    fresherHolder.bindClick(fresher, position);
                }

                break;
            case INTERN:
                if(list.get(position) instanceof Intern) {
                    Intern intern = (Intern) list.get(position);

                    InternHolder internHolder = (InternHolder) holder;
                    internHolder.tvName.setText(intern.getFullName());
                    internHolder.tvBirthDay.setText("Birthday: " + intern.getBirthDay());
                    internHolder.tvPhone.setText("Phone: " + intern.getPhone());
                    internHolder.tvEmail.setText("Email: " + intern.getEmail());
                    internHolder.tvMajor.setText("Major: " + intern.getMajor());
                    internHolder.tvSemester.setText("Semester: " + intern.getSemester());
                    internHolder.tvUniversityName.setText("University name: " + intern.getUniversityName());
                    internHolder.bindClick(intern, position);
                }

                break;
        }

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
                        if(emp.getEmployeeType().equals(strSearch)) {
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

    public class ExperienceHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvBirthDay, tvPhone, tvEmail, tvExpInYear, tvProSkill;
        private ImageButton btnEdit, btnDelete, btnCertificate;

        public ExperienceHolder(@NonNull View itemView) {
            super(itemView);

            initViewExperience();
        }

        private void initViewExperience() {
            tvName = itemView.findViewById(R.id.tvName);
            tvBirthDay = itemView.findViewById(R.id.tvBirthDay);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvExpInYear = itemView.findViewById(R.id.tvExpInYear);
            tvProSkill = itemView.findViewById(R.id.tvProSkill);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnCertificate = itemView.findViewById(R.id.btnCertificate);
        }

        public void bindClick(Experience experience, int position) {
            btnEdit.setOnClickListener(view -> {
                listener.employeeClick(experience, position );
            });

            btnDelete.setOnClickListener(view -> {
                list.remove(position);
                notifyDataSetChanged();
            });

            btnCertificate.setOnClickListener(view -> {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                dialogCertificate(experience);
            });
        }
    }

    public class FresherHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvBirthDay, tvPhone, tvEmail, tvGraduationDate, tvGraduationRank, tvEducation;
        private ImageButton btnEdit, btnDelete, btnCertificate;

        public FresherHolder(@NonNull View itemView) {
            super(itemView);

            initViewFresher();

        }

        private void initViewFresher() {
            tvName = itemView.findViewById(R.id.tvName1);
            tvBirthDay = itemView.findViewById(R.id.tvBirthDay1);
            tvPhone = itemView.findViewById(R.id.tvPhone1);
            tvEmail = itemView.findViewById(R.id.tvEmail1);
            tvGraduationDate = itemView.findViewById(R.id.tvGraduationDate);
            tvGraduationRank = itemView.findViewById(R.id.tvGraduationRank);
            tvEducation = itemView.findViewById(R.id.tvEducation);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnCertificate = itemView.findViewById(R.id.btnCertificate);
        }

        public void bindClick(Fresher fresher, int position) {
            btnEdit.setOnClickListener(view -> {
                listener.employeeClick(fresher, position );
            });

            btnDelete.setOnClickListener(view -> {
                list.remove(position);
                notifyDataSetChanged();
            });

            btnCertificate.setOnClickListener(view -> {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                dialogCertificate(fresher);
            });
        }
    }

    public class InternHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvBirthDay, tvPhone, tvEmail, tvMajor, tvSemester, tvUniversityName;
        private ImageButton btnEdit, btnDelete, btnCertificate;

        public InternHolder(@NonNull View itemView) {
            super(itemView);

            initViewIntern();

        }

        private void initViewIntern() {
            tvName = itemView.findViewById(R.id.tvName2);
            tvBirthDay = itemView.findViewById(R.id.tvBirthDay2);
            tvPhone = itemView.findViewById(R.id.tvPhone2);
            tvEmail = itemView.findViewById(R.id.tvEmail2);
            tvMajor = itemView.findViewById(R.id.tvMajor);
            tvSemester = itemView.findViewById(R.id.tvSemester);
            tvUniversityName = itemView.findViewById(R.id.tvUniversityName);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnCertificate = itemView.findViewById(R.id.btnCertificate);
        }

        public void bindClick(Intern intern, int position) {
            btnEdit.setOnClickListener(view -> {
                listener.employeeClick(intern, position );
            });

            btnDelete.setOnClickListener(view -> {
                list.remove(position);
                notifyDataSetChanged();
            });

            btnCertificate.setOnClickListener(view -> {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                dialogCertificate(intern);
            });
        }
    }

    private void dialogCertificate(Employee employee) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.certificate_dialog);
        TextView certificateName = dialog.findViewById(R.id.certificateName);
        TextView certificateRank = dialog.findViewById(R.id.certificateRank);
        TextView certificateDate = dialog.findViewById(R.id.certificateDate);

        certificateName.setText("Certificate name: " + employee.getCertificate().getCertificateName());
        certificateRank.setText("Certificate rank: " + employee.getCertificate().getCertificateRank());
        certificateDate.setText("Certificate date: " + employee.getCertificate().getCertificatedDate());
        dialog.show();
    }

}
