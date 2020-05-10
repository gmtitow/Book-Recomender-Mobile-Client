package c.tgm.booksapplication.promotion.add;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import java.util.Calendar;

import c.tgm.booksapplication.AbstractFragment;
import c.tgm.booksapplication.R;
import c.tgm.booksapplication.any.DataStore;
import c.tgm.booksapplication.databinding.FragmentPromotionAddBinding;

public class PromotionAddFragment extends AbstractFragment implements PromotionAddView {

    FragmentPromotionAddBinding mBinding;

    @InjectPresenter(type = PresenterType.LOCAL)
    PromotionAddPresenter mPresenter;

    //Переменные
    Calendar mTemporaryTimeEnd;
    Calendar mTemporaryTimeStart;
    //
    TimePickerDialog.OnTimeSetListener mOnTimeEndSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mTemporaryTimeEnd.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mTemporaryTimeEnd.set(Calendar.MINUTE, minute);

            mTemporaryTimeEnd.set(Calendar.SECOND, 0);

            Calendar today = Calendar.getInstance();
            today.set(Calendar.SECOND, 0);

            if (mTemporaryTimeEnd.getTime().getTime() > today.getTime().getTime()) {
                if (getPresenter().getTimeStart() == null ||mTemporaryTimeEnd.getTime().getTime() > getPresenter().getTimeStart().getTime().getTime()) {

                    getPresenter().setTimeEnd(mTemporaryTimeEnd);

                } else
                    Toast.makeText(getContext(), R.string.date_end_early_start_error, Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getContext(), R.string.task_date_end_error_today, Toast.LENGTH_SHORT).show();
        }
    };

    DatePickerDialog.OnDateSetListener mOnDateEndSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mTemporaryTimeEnd = Calendar.getInstance();
            mTemporaryTimeEnd.set(Calendar.YEAR, year);
            mTemporaryTimeEnd.set(Calendar.MONTH, month);
            mTemporaryTimeEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            if (getPresenter().getTimeEnd() != null)
                new TimePickerDialog(getContext(), mOnTimeEndSetListener,
                        getPresenter().getTimeEnd().get(Calendar.HOUR),
                        getPresenter().getTimeEnd().get(Calendar.MINUTE),
                        true)
                        .show();
            else {

                Calendar calendar = Calendar.getInstance();
                new TimePickerDialog(getContext(), mOnTimeEndSetListener,
                        calendar.get(Calendar.HOUR),
                        calendar.get(Calendar.MINUTE),
                        true)
                        .show();
            }
        }

    };

    TimePickerDialog.OnTimeSetListener mOnTimeStartSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mTemporaryTimeStart.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mTemporaryTimeEnd.set(Calendar.MINUTE, minute);

            mTemporaryTimeStart.set(Calendar.SECOND, 0);

            Calendar today = Calendar.getInstance();
            today.set(Calendar.SECOND, 0);

            if (getPresenter().getTimeEnd() == null || mTemporaryTimeStart.getTime().getTime() < getPresenter().getTimeEnd().getTime().getTime()) {

                getPresenter().setTimeEnd(mTemporaryTimeStart);

            } else
                Toast.makeText(getContext(), R.string.date_end_early_start_error, Toast.LENGTH_SHORT).show();
        }
    };

    DatePickerDialog.OnDateSetListener mOnDateStartSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mTemporaryTimeStart = Calendar.getInstance();
            mTemporaryTimeStart.set(Calendar.YEAR, year);
            mTemporaryTimeStart.set(Calendar.MONTH, month);
            mTemporaryTimeStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            if (getPresenter().getTimeStart() != null)
                new TimePickerDialog(getContext(), mOnTimeStartSetListener,
                        getPresenter().getTimeStart().get(Calendar.HOUR),
                        getPresenter().getTimeStart().get(Calendar.MINUTE),
                        true)
                        .show();
            else {

                Calendar calendar = Calendar.getInstance();
                new TimePickerDialog(getContext(), mOnTimeStartSetListener,
                        calendar.get(Calendar.HOUR),
                        calendar.get(Calendar.MINUTE),
                        true)
                        .show();
            }
        }

    };

    @Override
    public PromotionAddPresenter getPresenter() {
        return mPresenter;
    }


    @Override
    public boolean withEventBus() {
        return false;
    }

    @Override
    public String getTitle() {
        return "Создание акции";
    }

    @Override
    public boolean isChangeTitle() {
        return super.isChangeTitle();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void showMessage(String message) {
        super.showMessage(message);
    }

    public static Fragment getInstance() {
        return new PromotionAddFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_promotion_add, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
    }

    public void setupViews() {
        mBinding.textTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getPresenter().getTimeEnd() != null)
                    new DatePickerDialog(getContext(), mOnDateEndSetListener,
                            getPresenter().getTimeEnd().get(Calendar.YEAR),
                            getPresenter().getTimeEnd().get(Calendar.MONTH),
                            getPresenter().getTimeEnd().get(Calendar.DAY_OF_MONTH))
                            .show();
                else {
                    Calendar calendar = Calendar.getInstance();
                    new DatePickerDialog(getContext(), mOnDateEndSetListener,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH))
                            .show();
                }
            }
        });

        mBinding.textTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getPresenter().getTimeEnd() != null)
                    new DatePickerDialog(getContext(), mOnDateStartSetListener,
                            getPresenter().getTimeStart().get(Calendar.YEAR),
                            getPresenter().getTimeStart().get(Calendar.MONTH),
                            getPresenter().getTimeStart().get(Calendar.DAY_OF_MONTH))
                            .show();
                else {
                    Calendar calendar = Calendar.getInstance();
                    new DatePickerDialog(getContext(), mOnDateStartSetListener,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH))
                            .show();
                }
            }
        });
    }

    @Override
    public void onSetTimeEnd(Calendar timeEnd) {
        mBinding.textTimeEnd.setText(DataStore.mFormatFromServer.format(timeEnd));
    }

    @Override
    public void onSetTimeStart(Calendar timeStart) {
        mBinding.textTimeEnd.setText(DataStore.mFormatFromServer.format(timeStart));
    }
}
