package com.quytech.testdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.quytech.dao.Employee;
import com.quytech.db.JDBcH2Connection;


public class MainActivity extends Activity 
{
	
	private TextView	textView;
	private EditText	emp_name;
	private EditText 	dob;
	private EditText 	salary;
	private EditText 	dept_id;
	private Button  	save;
	private Button		fetch;
	//JDBcH2Connection jdBcH2Connection	= null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView =(TextView)findViewById(R.id.text); 
		emp_name = (EditText) findViewById(R.id.editText_name);
		dob = (EditText) findViewById(R.id.editText_dob);
		salary = (EditText) findViewById(R.id.editText_sal);
		dept_id = (EditText) findViewById(R.id.editText_depid);
		save = (Button) findViewById(R.id.btn_save);
		fetch = (Button) findViewById(R.id.btn_fetch);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setConnectionForH2();
			}
		});
		fetch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				StringBuffer buffer=new StringBuffer();
				List<Employee> employee=getEmployee();
				for (Employee emp:employee) 
				{
					
					buffer.append("Name :"+emp.getEmpName()+" Dob :"+emp.getDob()+" Salary :"+emp.getSalary()+
							" DeptID :"+emp.getDeptId());
					buffer.append("\n");
				}
				textView.setText(buffer.toString());
			}
		});
	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
	private void setConnectionForH2()
	{
		
			try
			{
				
				Connection conn  = JDBcH2Connection.getConnection();
				//Statement stat 	 = conn.createStatement();
			    String sql = "INSERT INTO employee (emp_name, dob, salary,dept_id)" +
					        "VALUES (?, ?, ? ,?)";
				
				  PreparedStatement preparedStatement = conn.prepareStatement(sql);
				  preparedStatement.setString(1, emp_name.getText().toString()!=null?emp_name.getText().toString():"Test");
				  preparedStatement.setString(2, dob.getText().toString()!=null?dob.getText().toString():"Test");
				  preparedStatement.setString(3, salary.getText().toString()!=null?salary.getText().toString():"Test");
				  preparedStatement.setString(4, dept_id.getText().toString()!=null?dept_id.getText().toString():"Test");
				  preparedStatement.executeUpdate();  
				  JDBcH2Connection.close(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public List<Employee> getEmployee()
	{      
        ResultSet rs 			= null;
        Connection connection 	= null;
        Statement statement 	= null; 
        
      List<Employee> employees=new ArrayList<Employee>();
         
        Employee employee = null;
        String query = "SELECT * FROM employee ";;//WHERE emp_id=" + employeeId;
        try {           
            connection = JDBcH2Connection.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
             
            while (rs.next())
            {
                employee = new Employee();
                employee.setEmpId(rs.getInt("emp_id"));
                employee.setEmpName(rs.getString("emp_name"));
                employee.setDob(rs.getString("dob"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setDeptId((rs.getInt("dept_id")));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return employees;
    }
	private void insertDataToDB()
	{
		
	}
}
