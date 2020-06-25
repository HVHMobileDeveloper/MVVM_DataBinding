package com.devfptpoly.admin.databinding;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRepository {
    private static final String TAG = "EmployeeRepository";
    private ArrayList<Employee> employees = new ArrayList<>();
    private MutableLiveData<List<Employee>> mutableLiveData = new MutableLiveData<>();
    public EmployeeRepository() {
    }
    public MutableLiveData<List<Employee>> getMutableLiveData() {
        final EmployeeDataService userDataService = RetrofitClient.getService();
        Call<EmployeeDBResponse> call = userDataService.getEmployees();
        call.enqueue(new Callback<EmployeeDBResponse>() {
            @Override
            public void onResponse(Call<EmployeeDBResponse> call, Response<EmployeeDBResponse> response) {
                EmployeeDBResponse employeeDBResponse = response.body();
                if (employeeDBResponse != null && employeeDBResponse.getEmployee() != null) {
                    employees = (ArrayList<Employee>) employeeDBResponse.getEmployee();
                    mutableLiveData.setValue(employees);
                }
            }
            @Override
            public void onFailure(Call<EmployeeDBResponse> call, Throwable t) {
            }
        });
        return mutableLiveData;
    }
}
