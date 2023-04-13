package System.IMS.repository.packageSlipDao;

import System.IMS.entity.PackageSlip;

import java.util.List;

public interface PackageSlipDao {
        List<PackageSlip> getAllPackageSlips();
        PackageSlip getPackageSlipById(Long id);
        void addPackageSlip(PackageSlip packageSlip);
        void updatePackageSlip(PackageSlip packageSlip);
        void deletePackageSlip(PackageSlip packageSlip);
}
